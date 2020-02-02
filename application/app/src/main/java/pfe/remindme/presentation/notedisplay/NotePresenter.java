package pfe.remindme.presentation.notedisplay;

import android.provider.ContactsContract;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
import pfe.remindme.data.Tag;
import pfe.remindme.data.entity.NoteEntity;
import pfe.remindme.data.repository.notedisplay.NoteDisplayRepository;
import pfe.remindme.data.repository.notedisplay.mapper.NoteEntityToNoteMapper;
import pfe.remindme.data.repository.notedisplay.mapper.NoteToNoteEntityMapper;
import pfe.remindme.presentation.notedisplay.adapter.NoteItemViewModel;
import pfe.remindme.presentation.notedisplay.mapper.NoteToViewModelMapper;



public class NotePresenter implements NoteContract.Presenter {
    private NoteContract.View view;
    private NoteToViewModelMapper noteToViewModelMapper;
    private NoteToNoteEntityMapper noteToNoteEntityMapper;
    private NoteEntityToNoteMapper noteEntityToNoteMapper;
    private NoteDisplayRepository noteDisplayRepository;
    private CompositeDisposable compositeDisposable;

    private List<Note> currentNotes;

    public NotePresenter(NoteDisplayRepository repo,
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
    public void attachView(NoteContract.View view) {
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
                        view.setNotes(noteToViewModelMapper.map(noteList));
                        view.displayNotes();
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
        final Note n = new Note(note_content);
        final NoteEntity noteEntity = noteToNoteEntityMapper.map(n);

        compositeDisposable.add(noteDisplayRepository.addNote(noteEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Long>() {

                    @Override
                    public void onSuccess(Long aLong) {
                        Note addedNote = noteEntityToNoteMapper.map(noteEntity);
                        addedNote.setId(aLong.intValue());
                        view.onNoteAdded(addedNote);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
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
                        view.setNotesByIdList(notes);
                        view.displayNotes();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setNotes(new ArrayList<NoteItemViewModel>());
                        view.displayNotes();
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

    @Override
    public void getTag(final String tagName, final Note note) {
        compositeDisposable.add(noteDisplayRepository.getTagByTagName(tagName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Tag>() {

                    @Override
                    public void onSuccess(Tag tag) {
                        Tag tagToUpdate;
                        if (tag == null) {
                            tagToUpdate = new Tag(tagName);
                            tagToUpdate.addNote(note.getId());
                            addTag(tagToUpdate);
                        } else {
                            tagToUpdate = tag;
                            tagToUpdate.addNote(note.getId());
                            updateTag(tagToUpdate);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e.getMessage().contains("returned empty result set")) {
                            Tag tagToUpdate;
                            tagToUpdate = new Tag(tagName);
                            tagToUpdate.addNote(note.getId());
                            addTag(tagToUpdate);
                        } else {
                            e.printStackTrace();
                        }
                    }
                })
        );
    }


    @Override
    public void getTagByTagName(String tagName) {
        compositeDisposable.add(noteDisplayRepository.getTagByTagName(tagName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Tag>() {

                    @Override
                    public void onSuccess(Tag t) {
                        List<Note> notes = new ArrayList<>();
                        notes.add(new Note(t.getTagName()+ " /// "+t.getLinkedNotes().toString()));
                        view.setNotes(noteToViewModelMapper.map(notes));
                        view.displayNotes();
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                })
        );
    }

    @Override
    public void updateTag(Tag tag) {
        compositeDisposable.add(noteDisplayRepository.updateTag(tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        System.out.println("hi");
                        //Do nothing
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                })
        );
    }

    @Override
    public void addTag(Tag tag) {
        compositeDisposable.add(noteDisplayRepository.addTag(tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        System.out.println("hi");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                })
        );
    }

    @Override
    public void getTagDatabase() {
        compositeDisposable.add(noteDisplayRepository.getTagDatabase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<Tag>>() {

                    @Override
                    public void onNext(List<Tag> tags) {
                        List<Note> notes = new ArrayList<>();
                        for (Tag t : tags) {
                            notes.add(new Note(t.getTagName()+ " /// "+t.getLinkedNotes().toString()));
                        }
                        view.setNotes(noteToViewModelMapper.map(notes));
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
    public void getNoteById(int noteId) {
        compositeDisposable.add(noteDisplayRepository.getNoteById(noteId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Note>() {

                    @Override
                    public void onSuccess(Note n) {
                        List<Note> notes = new ArrayList<>();
                        notes.add(n);
                        view.setNotes(noteToViewModelMapper.map(notes));
                        view.displayNotes();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                })
        );
    }


}
