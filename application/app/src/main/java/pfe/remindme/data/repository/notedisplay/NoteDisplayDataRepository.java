package pfe.remindme.data.repository.notedisplay;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import pfe.remindme.data.DataConverter;
import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;
import pfe.remindme.data.entity.NoteEntity;
import pfe.remindme.data.entity.TagEntity;
import pfe.remindme.data.repository.notedisplay.local.NoteDisplayLocalDataSource;
import pfe.remindme.data.repository.notedisplay.mapper.NoteEntityToNoteMapper;
import pfe.remindme.data.repository.notedisplay.mapper.NoteToNoteEntityMapper;
import pfe.remindme.data.repository.notedisplay.mapper.TagToTagEntityMapper;
import pfe.remindme.data.repository.notedisplay.remote.NoteDisplayRemoteDataSource;

public class NoteDisplayDataRepository implements NoteDisplayRepository {
    private NoteDisplayLocalDataSource noteDisplayLocalDataSource;
    private NoteDisplayRemoteDataSource noteDisplayRemoteDataSource;
    private NoteToNoteEntityMapper noteToNoteEntityMapper;
    private TagToTagEntityMapper tagToTagEntityMapper;
    private NoteEntityToNoteMapper noteEntityToNoteMapper;

    public NoteDisplayDataRepository(
        NoteDisplayLocalDataSource noteDisplayLocalDataSource,
        NoteDisplayRemoteDataSource noteDisplayRemoteDataSource,
        NoteToNoteEntityMapper noteToNoteEntityMapper,
        TagToTagEntityMapper tagToTagEntityMapper,
        NoteEntityToNoteMapper noteEntityToNoteMapper
    )
    {
        this.noteDisplayLocalDataSource = noteDisplayLocalDataSource;
        this.noteDisplayRemoteDataSource = noteDisplayRemoteDataSource;
        this.noteToNoteEntityMapper = noteToNoteEntityMapper;
        this.tagToTagEntityMapper = tagToTagEntityMapper;
        this.noteEntityToNoteMapper = noteEntityToNoteMapper;
    }

    @Override
    public Single<NoteEntity> getNoteById(int noteId) {
        return noteDisplayLocalDataSource.loadNote(noteId);
    }

    @Override
    public Single<TagEntity> getTagByTagName(String tagName) {
        return noteDisplayLocalDataSource.loadTag(tagName);
    }

    @Override
    public Single<String> getTagDatabaseAsJson() {
        return noteDisplayLocalDataSource.getAllTagsAsJson();
    }

    // TODO : CHANGER LA DATABASE POUR RECUP CEUX LA
    @Override
    public Flowable<List<TagEntity>> getTagsFromNote(int noteId) {
        return null;
    }

    @Override
    public Flowable<List<NoteEntity>> getLinkedNotesFromTag(String tagName) {
        return null;
    }

     ///////////////////////////////////

    @Override
    public Completable addNote(Note note) {
        return null;
    }

    @Override
    public Completable removeNote(int noteid) {
        return null;
    }
}
