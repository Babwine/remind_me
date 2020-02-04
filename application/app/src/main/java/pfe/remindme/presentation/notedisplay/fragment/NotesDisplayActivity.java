package pfe.remindme.presentation.notedisplay.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import pfe.remindme.R;
import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;
import pfe.remindme.data.di.FakeDependencyInjection;
import pfe.remindme.data.repository.notedisplay.mapper.NoteEntityToNoteMapper;
import pfe.remindme.data.repository.notedisplay.mapper.NoteToNoteEntityMapper;
import pfe.remindme.presentation.notecapture.fragment.NotesCaptureActivity;
import pfe.remindme.presentation.notedisplay.NoteContract;
import pfe.remindme.presentation.notedisplay.NotePresenter;
import pfe.remindme.presentation.notedisplay.adapter.NoteActionInterface;
import pfe.remindme.presentation.notedisplay.adapter.NoteAdapter;
import pfe.remindme.presentation.notedisplay.adapter.NoteItemViewModel;
import pfe.remindme.presentation.notedisplay.mapper.NoteToViewModelMapper;

public class NotesDisplayActivity extends AppCompatActivity implements NoteContract.View, NoteActionInterface {
    private NoteContract.Presenter notePresenter;
    private NoteAdapter noteAdapter;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private ProgressBar progressBar;

    private List<NoteItemViewModel> currentNotes;

    private FloatingActionButton addNoteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        FakeDependencyInjection.setContext(this);


        currentNotes = new ArrayList<>();
        noteAdapter = new NoteAdapter(this);
        notePresenter = new NotePresenter(FakeDependencyInjection.getNoteDisplayRepository(), new NoteToViewModelMapper(), new NoteToNoteEntityMapper(), new NoteEntityToNoteMapper());
        setupSearchView();
        setupRecyclerView();
        progressBar = findViewById(R.id.progress_bar);

        notePresenter.attachView(this);

        notePresenter.displayAllNotes();

        addNoteButton = findViewById(R.id.addNoteButton);

        setupListeners();

    }

    private void setupListeners() {
        addNoteButton.setOnClickListener(new FloatingActionButton.OnClickListener() {

            @Override
            public void onClick(View v) {
                switchActivity(v);
            }
        });
    }

    private void switchActivity(View v) {
        Intent intent = new Intent(this, NotesCaptureActivity.class);
        startActivity(intent);
    }

    private void setupSearchView() {
        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            private Timer timer = new Timer();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String s) {
                if (s.length() == 0) {
                    notePresenter.displayAllNotes();
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    timer.cancel();
                    timer = new Timer();
                    int sleep = 350;
                    if (s.length() == 1)
                        sleep = 500;
                    else if (s.length() <= 3)
                        sleep = 300;
                    else if (s.length() <= 5)
                        sleep = 200;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (s.length() == 0) notePresenter.displayAllNotes();
                            else notePresenter.displayNotesFromStringLikeTag(s);
                        }
                    }, sleep);
                }
                return true;
            }
        });
    }



    private void setupRecyclerView() {
        recyclerView = this.findViewById(R.id.recyclerView);
        noteAdapter = new NoteAdapter(this);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        notePresenter.detachView();
    }


    @Override
    public void displayNotes() {
        noteAdapter.bindViewModels(currentNotes);
    }

    @Override
    public void setNotes(List<NoteItemViewModel> notes) {
        this.currentNotes = notes;
    }

    @Override
    public void setNotesByIdList(List<Integer> noteIdList) {
        notePresenter.displayNotesFromIdList(noteIdList);
    }

    @Override
    public void onNoteAdded(Note note) {
        for (String tagName : note.getTags()) {
            notePresenter.getTag(tagName, note);
        }
    }

    @Override
    public void onNoteDeleted() {

    }

    @Override
    public void getTag(Tag tag, Note note) {

    }


    @Override
    public void onNoteDeleted(final int noteId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Voulez-vous vraiment supprimer cette note ?");
        builder.setTitle("Suppression de note");
        builder.setCancelable(false);

        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                notePresenter.removeNote(noteId);
                dialog.dismiss();
                if (searchView.getQuery().toString().length() == 0) notePresenter.displayAllNotes();
                else notePresenter.displayNotesFromStringLikeTag(searchView.getQuery().toString());
            }
        });

        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        builder.show();
    }
}
