package pfe.remindme.presentation.notedisplay;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import pfe.remindme.data.DataConverter;
import pfe.remindme.data.Note;
import pfe.remindme.data.repository.notedisplay.NoteDisplayRepository;
import pfe.remindme.data.repository.notedisplay.mapper.NoteEntityToNoteMapper;
import pfe.remindme.data.repository.notedisplay.mapper.NoteToNoteEntityMapper;
import pfe.remindme.presentation.notedisplay.mapper.NoteToViewModelMapper;



public class NoteDisplayPresenter implements NoteDisplayContract.Presenter {
    private NoteDisplayContract.View view;
    private NoteToViewModelMapper noteToViewModelMapper;
    private NoteToNoteEntityMapper noteToNoteEntityMapper;
    private NoteEntityToNoteMapper noteEntityToNoteMapper;
    private NoteDisplayRepository noteDisplayRepository;
    private CompositeDisposable compositeDisposable;

    private List<Note> currentNotes;

    public NoteDisplayPresenter(NoteDisplayRepository repo,
                                NoteToViewModelMapper noteToViewModelMapper,
                                NoteToNoteEntityMapper noteToNoteEntityMapper,
                                NoteEntityToNoteMapper noteEntityToNoteMapper) {
        this.noteToViewModelMapper = noteToViewModelMapper;
        this.noteDisplayRepository = repo;
        this.compositeDisposable = new CompositeDisposable();
        this.noteToNoteEntityMapper = noteToNoteEntityMapper;
        this.noteEntityToNoteMapper = noteEntityToNoteMapper;
    }


    @Override
    public void attachView(NoteDisplayContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void displayAllNotes() {
        compositeDisposable.add(noteDisplayRepository.getAllNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<Note>>() {

                    @Override
                    public void onNext(List<Note> noteList) {
                        if (view != null) {
                            view.setNotes(noteToViewModelMapper.map(noteList));
                            view.displayNotes();
                        }
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
    public void displayNotesFromStringLikeTag(final String str) {
        compositeDisposable.add(noteDisplayRepository.getLinkedNotesIdFromStringLikeTagAsJson(str)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<String>>() {


                    @Override
                    public void onNext(List<String> stringList) {
                        DataConverter dc = new DataConverter();
                        List<Integer> notes = new ArrayList<>();
                        for (String json : stringList) {
                            List<Integer> tmp = dc.toNoteIdList(json);
                            for (Integer noteId : tmp) {
                                if (!notes.contains(noteId)) {
                                    notes.add(noteId);
                                }
                            }
                        }
                        view.setNotesByIdList(notes);
                        view.displayNotes();
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

    @Override
    public void displayNotesFromIdList(List<Integer> noteIdList) {
        compositeDisposable.add(noteDisplayRepository.getNotesFromIdList(noteIdList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<Note>>() {

                    @Override
                    public void onNext(List<Note> noteList) {
                        view.setNotes(noteToViewModelMapper.map(noteList));
                        view.displayNotes();
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );
    }

    @Override
    public void deleteAllNotes() {
        compositeDisposable.add(noteDisplayRepository.deleteAllNotes()
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
    public void deleteAllTags() {
        compositeDisposable.add(noteDisplayRepository.deleteAllTags()
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



}
