package pfe.remindme.presentation.notedisplay;

import android.provider.ContactsContract;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import pfe.remindme.data.DataConverter;
import pfe.remindme.data.Note;
import pfe.remindme.data.entity.NoteEntity;
import pfe.remindme.data.repository.notedisplay.NoteDisplayRepository;
import pfe.remindme.data.repository.notedisplay.mapper.NoteToNoteEntityMapper;
import pfe.remindme.presentation.notedisplay.mapper.NoteToViewModelMapper;



public class NotePresenter implements NoteContract.Presenter {
    private NoteContract.View view;
    private NoteToViewModelMapper noteToViewModelMapper;
    private NoteToNoteEntityMapper noteToNoteEntityMapper;
    private NoteDisplayRepository noteDisplayRepository;
    private CompositeDisposable compositeDisposable;

    private List<Note> currentNotes;

    public NotePresenter(NoteDisplayRepository repo,
                         NoteToViewModelMapper noteToViewModelMapper,
                         NoteToNoteEntityMapper noteToNoteEntityMapper) {
        this.noteToViewModelMapper = noteToViewModelMapper;
        this.noteDisplayRepository = repo;
        this.compositeDisposable = new CompositeDisposable();
        this.noteToNoteEntityMapper = noteToNoteEntityMapper;
    }


    @Override
    public void attachView(NoteContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void displayNotes() {
        compositeDisposable.add(noteDisplayRepository.getAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<Note>>() {

                    @Override
                    public void onNext(List<Note> noteList) {
                        view.displayNotes(noteToViewModelMapper.map(noteList));
                        System.out.println("DISPLAY NOTES");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        //Do Nothing
                    }
                }));
    }

    @Override
    public void addNote(String note_content) {
        Note n = new Note(note_content);

        compositeDisposable.add(noteDisplayRepository.addNote(noteToNoteEntityMapper.map(n))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onNoteAdded();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                })
        );
    }

    @Override
    public void removeNote(int noteId) {
        compositeDisposable.add(noteDisplayRepository.removeNote(noteId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                })
        );
    }

    @Override
    public void displayNotesFromTag(String tagName) {
        compositeDisposable.add(noteDisplayRepository.getLinkedNotesIdFromTagAsJson(tagName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<String>() {

                    @Override
                    public void onSuccess(String s) {
                        DataConverter dc = new DataConverter();
                        List<Integer> notes = dc.toNoteIdList(s);
                        //TODO : récupérer ces notes pour les passer à la méthode displayNotesFromIdList
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                })
        );
    }

    @Override
    public void displayNotesFromIdList(List<Integer> noteIdList) {
        compositeDisposable.add(noteDisplayRepository.getNotesFromIdList(noteIdList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<Note>>() {

                    @Override
                    public void onNext(List<Note> noteList) {
                        view.displayNotes(noteToViewModelMapper.map(noteList));
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );
    }
}
