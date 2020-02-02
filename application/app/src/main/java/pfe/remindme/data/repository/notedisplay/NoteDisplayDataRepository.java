package pfe.remindme.data.repository.notedisplay;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;
import pfe.remindme.data.entity.NoteEntity;
import pfe.remindme.data.entity.TagEntity;
import pfe.remindme.data.repository.notedisplay.local.NoteDisplayLocalDataSource;
import pfe.remindme.data.repository.notedisplay.mapper.NoteEntityToNoteMapper;
import pfe.remindme.data.repository.notedisplay.mapper.NoteToNoteEntityMapper;
import pfe.remindme.data.repository.notedisplay.mapper.TagEntityToTagMapper;
import pfe.remindme.data.repository.notedisplay.mapper.TagToTagEntityMapper;
import pfe.remindme.data.repository.notedisplay.remote.NoteDisplayRemoteDataSource;

public class NoteDisplayDataRepository implements NoteDisplayRepository {
    private NoteDisplayLocalDataSource noteDisplayLocalDataSource;
    private NoteDisplayRemoteDataSource noteDisplayRemoteDataSource;
    private NoteToNoteEntityMapper noteToNoteEntityMapper;
    private TagToTagEntityMapper tagToTagEntityMapper;
    private NoteEntityToNoteMapper noteEntityToNoteMapper;
    private TagEntityToTagMapper tagEntityToTagMapper;

    public NoteDisplayDataRepository(
        NoteDisplayLocalDataSource noteDisplayLocalDataSource,
        NoteDisplayRemoteDataSource noteDisplayRemoteDataSource,
        NoteToNoteEntityMapper noteToNoteEntityMapper,
        TagToTagEntityMapper tagToTagEntityMapper,
        NoteEntityToNoteMapper noteEntityToNoteMapper,
        TagEntityToTagMapper tagEntityToTagMapper
    )
    {
        this.noteDisplayLocalDataSource = noteDisplayLocalDataSource;
        this.noteDisplayRemoteDataSource = noteDisplayRemoteDataSource;
        this.noteToNoteEntityMapper = noteToNoteEntityMapper;
        this.tagToTagEntityMapper = tagToTagEntityMapper;
        this.noteEntityToNoteMapper = noteEntityToNoteMapper;
        this.tagEntityToTagMapper = tagEntityToTagMapper;
    }

    @Override
    public Single<Note> getNoteById(int noteId) {
        return noteDisplayLocalDataSource.loadNote(noteId).map(
                new Function<NoteEntity, Note>() {
                    @Override
                    public Note apply(NoteEntity noteEntity) throws Exception {
                        return noteEntityToNoteMapper.map(noteEntity);
                    }
                }
        );
    }

    @Override
    public Flowable<List<Note>> getAllNotes() {
        return noteDisplayLocalDataSource.loadAllNotes().map(
                new Function<List<NoteEntity>, List<Note>>() {
                    @Override
                    public List<Note> apply(List<NoteEntity> noteEntityList) throws Exception {
                        return noteEntityToNoteMapper.map(noteEntityList);
                    }
                }
        );
    }

    @Override
    public Single<Tag> getTagByTagName(String tagName) {
        return noteDisplayLocalDataSource.loadTag(tagName).map(
                new Function<TagEntity, Tag>() {
                    @Override
                    public Tag apply(TagEntity tagEntity) throws Exception {
                        return tagEntityToTagMapper.map(tagEntity);
                    }
                }
        );
    }

    @Override
    public Flowable<List<Tag>> getTagDatabase() {
        return noteDisplayLocalDataSource.getAllTags().map(
                new Function<List<TagEntity>, List<Tag>>() {
                    @Override
                    public List<Tag> apply(List<TagEntity> tagEntityList) throws Exception {
                        return tagEntityToTagMapper.map(tagEntityList);
                    }
                }
        );
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
    public Single<Long> addNote(NoteEntity noteEntity) {
        return noteDisplayLocalDataSource.addNote(noteEntity);
    }

    @Override
    public Completable updateTag(Tag tag) {
        return noteDisplayLocalDataSource.updateTag(tagToTagEntityMapper.map(tag));
    }

    @Override
    public Completable removeNote(int noteId) {
        return noteDisplayLocalDataSource.removeNote(noteId);
    }

    @Override
    public Flowable<List<Note>> getNotesFromIdList(List<Integer> noteIdList) {
        return noteDisplayLocalDataSource.getNotesFromIdList(noteIdList).map(
                new Function<List<NoteEntity>, List<Note>>() {
                    @Override
                    public List<Note> apply(List<NoteEntity> noteEntities) throws Exception {
                        return noteEntityToNoteMapper.map(noteEntities);
                    }
                }
        );
    }

    @Override
    public Completable deleteAllNotes() {
        return noteDisplayLocalDataSource.deleteAllNotes();
    }

    @Override
    public Completable deleteAllTags() {
        return noteDisplayLocalDataSource.deleteAllTags();
    }

    @Override
    public Completable addTag(Tag tag) {
        return noteDisplayLocalDataSource.addtag(tagToTagEntityMapper.map(tag));
    }
}
