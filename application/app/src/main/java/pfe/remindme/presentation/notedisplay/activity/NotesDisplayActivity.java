package pfe.remindme.presentation.notedisplay.activity;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import pfe.remindme.R;
import pfe.remindme.data.di.FakeDependencyInjection;
import pfe.remindme.data.repository.notedisplay.mapper.NoteEntityToNoteMapper;
import pfe.remindme.data.repository.notedisplay.mapper.NoteToNoteEntityMapper;
import pfe.remindme.presentation.notedisplay.NoteDisplayContract;
import pfe.remindme.presentation.notedisplay.NoteDisplayPresenter;
import pfe.remindme.presentation.notedisplay.adapter.NoteActionInterface;
import pfe.remindme.presentation.notedisplay.adapter.NoteAdapter;
import pfe.remindme.presentation.notedisplay.adapter.NoteItemViewModel;
import pfe.remindme.presentation.notedisplay.mapper.NoteToViewModelMapper;

public class NotesDisplayActivity extends AppCompatActivity implements NoteDisplayContract.View, NoteActionInterface {
    private NoteDisplayContract.Presenter noteDisplayPresenter;
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
        noteDisplayPresenter = new NoteDisplayPresenter(FakeDependencyInjection.getNoteDisplayRepository(), new NoteToViewModelMapper(), new NoteToNoteEntityMapper(), new NoteEntityToNoteMapper());
        setupSearchView();
        setupRecyclerView();
        progressBar = findViewById(R.id.progress_bar);
        addNoteButton = findViewById(R.id.addNoteButton);

        noteDisplayPresenter.attachView(this);
        noteDisplayPresenter.displayAllNotes();

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
        finish();
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
                    noteDisplayPresenter.displayAllNotes();
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    timer.cancel();
                    timer = new Timer();
                    int sleep = 100;
                    /*
                    if (s.length() == 1)
                        sleep = 500;
                    else if (s.length() <= 3)
                        sleep = 300;
                    else if (s.length() <= 5)
                        sleep = 200;
                    */
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            noteDisplayPresenter.displayNotesFromStringLikeTag(s);
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
        noteDisplayPresenter.detachView();
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
        noteDisplayPresenter.displayNotesFromIdList(noteIdList);
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
                noteDisplayPresenter.removeNote(noteId);
                dialog.dismiss();
                if (searchView.getQuery().toString().length() == 0) noteDisplayPresenter.displayAllNotes();
                else noteDisplayPresenter.displayNotesFromStringLikeTag(searchView.getQuery().toString());
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
