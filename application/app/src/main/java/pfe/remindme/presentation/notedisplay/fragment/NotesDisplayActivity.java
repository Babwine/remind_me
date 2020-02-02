package pfe.remindme.presentation.notedisplay.fragment;

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

import pfe.remindme.R;
import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;
import pfe.remindme.data.di.FakeDependencyInjection;
import pfe.remindme.data.repository.notedisplay.mapper.NoteEntityToNoteMapper;
import pfe.remindme.data.repository.notedisplay.mapper.NoteToNoteEntityMapper;
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

    private List<NoteItemViewModel> currentNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        FakeDependencyInjection.setContext(this);


        currentNotes = new ArrayList<>();
        noteAdapter = new NoteAdapter(this);
        notePresenter = new NotePresenter(FakeDependencyInjection.getNoteDisplayRepository(), new NoteToViewModelMapper(), new NoteToNoteEntityMapper(), new NoteEntityToNoteMapper());
        setupRecyclerView();

        notePresenter.attachView(this);

        //TESTER ICI POUR AJOUT MANUEL DE NOTES


        //notePresenter.deleteAllNotes();
        //notePresenter.deleteAllTags();

        //notePresenter.addNote("rangé chien niche");
        //notePresenter.addNote("acheter brosse dents");
        //notePresenter.addNote("acheter trousse toilettes");
        


        //notePresenter.displayAllNotes();

        //notePresenter.getTagDatabase();



        notePresenter.displayNotesFromTag("acheter");

        //notePresenter.getNoteById(30);

        //notePresenter.getTagById(-1380608122);

        //notePresenter.getTagByTagName("acheter");


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




}
