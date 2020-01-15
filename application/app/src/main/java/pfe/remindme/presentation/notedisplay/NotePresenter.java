package pfe.remindme.presentation.notedisplay;

import java.util.List;

import pfe.remindme.data.Note;
import pfe.remindme.data.repository.notedisplay.NoteDisplayRepository;
import pfe.remindme.presentation.notedisplay.mapper.NoteToViewModelMapper;



public class NotePresenter implements NoteContract.Presenter {
    private NoteContract.View view;
    private NoteToViewModelMapper mapper;
    private NoteDisplayRepository pokemonDisplayRepository;

    public NotePresenter(NoteDisplayRepository repo, NoteToViewModelMapper mapper) {
        this.mapper = mapper;
        this.pokemonDisplayRepository = repo;
    }

    //TODO : addNote qui utilise repo.addNote et updateTag

    @Override
    public void attachView(NoteContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void displayNotes(List<Note> noteList) {
        view.displayNotes(mapper.map(noteList));
    }
}
