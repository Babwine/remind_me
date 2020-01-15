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

    public NoteDisplayDataRepository(
        NoteDisplayLocalDataSource noteDisplayLocalDataSource,
        NoteDisplayRemoteDataSource noteDisplayRemoteDataSource,
        NoteToNoteEntityMapper noteToNoteEntityMapper,
        TagToTagEntityMapper tagToTagEntityMapper
    )
    {
        this.noteDisplayLocalDataSource = noteDisplayLocalDataSource;
        this.noteDisplayRemoteDataSource = noteDisplayRemoteDataSource;
        this.noteToNoteEntityMapper = noteToNoteEntityMapper;
        this.tagToTagEntityMapper = tagToTagEntityMapper;
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


    @Override
    public Single<String> getTagsFromNoteAsJson(int noteId) {
        return noteDisplayLocalDataSource.getTagsFromNoteAsJson(noteId);
    }

    @Override
    public Single<String> getLinkedNotesIdFromTagAsJson(String tagName) {
        return noteDisplayLocalDataSource.getLinkedNotesIdFromTagAsJson(tagName);
    }


    @Override
    public Completable addNote(Note note) {
        return noteDisplayLocalDataSource.addNote(noteToNoteEntityMapper.map(note));
    }

    @Override
    public Completable removeNote(int noteId) {
        return noteDisplayLocalDataSource.removeNote(noteId);
    }
}
