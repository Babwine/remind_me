package pfe.remindme;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;
import pfe.remindme.data.di.FakeDependencyInjection;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


        noteAdapter = new NoteAdapter(this);
        notePresenter = new NotePresenter(FakeDependencyInjection.getPokemonDisplayRepository(), new NoteToViewModelMapper());
        notePresenter.attachView(this);
        setupRecyclerView();

        //TODO TMP

        List<Tag> tagDatabase = new ArrayList<>();

        List<Note> noteList = new ArrayList<>();

        Note note1 = new Note("rangé chien niche", tagDatabase);
        Note note2 = new Note("acheter croquettes chien", tagDatabase);
        Note note3 = new Note("acheter brosse dents", tagDatabase);

        noteList.add(note1);
        noteList.add(note2);
        noteList.add(note3);

        notePresenter.attachView(this);
        notePresenter.displayNotes(noteList);
    }



    /**
     * A function to setup the recycler view in which the Pokemon's main view is
     */
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
    public void displayNotes(List<NoteItemViewModel> noteItemViewModelList) {
        noteAdapter.bindViewModels(noteItemViewModelList);
    }
}
