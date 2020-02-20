package pfe.remindme.presentation.notecapture;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;
import pfe.remindme.data.entity.NoteEntity;
import pfe.remindme.data.repository.notedisplay.NoteDisplayRepository;
import pfe.remindme.data.repository.notedisplay.mapper.NoteEntityToNoteMapper;
import pfe.remindme.data.repository.notedisplay.mapper.NoteToNoteEntityMapper;

/**
 * La classe du présenteur lié à l'activité de capture des notes
 */
public class NoteCapturePresenter implements NoteCaptureContract.Presenter {
    private NoteCaptureContract.View view;
    private NoteToNoteEntityMapper noteToNoteEntityMapper;
    private NoteEntityToNoteMapper noteEntityToNoteMapper;
    private NoteDisplayRepository noteDisplayRepository;
    private CompositeDisposable compositeDisposable;

    /**
     * Constructeur
     * @param repo le repository de l'application
     * @param noteToNoteEntityMapper le mapper qui transforme une Note en NoteEntity
     * @param noteEntityToNoteMapper le mapper qui transforme une NoteEntity en Note
     */
    public NoteCapturePresenter(NoteDisplayRepository repo,
                         NoteToNoteEntityMapper noteToNoteEntityMapper,
                         NoteEntityToNoteMapper noteEntityToNoteMapper) {
        this.noteDisplayRepository = repo;
        this.compositeDisposable = new CompositeDisposable();
        this.noteToNoteEntityMapper = noteToNoteEntityMapper;
        this.noteEntityToNoteMapper = noteEntityToNoteMapper;
    }

    @Override
    public void attachView(NoteCaptureContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
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
    public void updateTag(Tag tag) {
        compositeDisposable.add(noteDisplayRepository.updateTag(tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {

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

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
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
    public void getLastAddedNote() {
        compositeDisposable.add(noteDisplayRepository.getLastAddedNote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Note>() {

                    @Override
                    public void onSuccess(Note note) {
                        view.displayLastAddedNote(note);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                })
        );
    }
}
