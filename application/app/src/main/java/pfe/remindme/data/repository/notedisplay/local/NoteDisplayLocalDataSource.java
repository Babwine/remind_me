package pfe.remindme.data.repository.notedisplay.local;

import io.reactivex.Completable;
import io.reactivex.Single;
import pfe.remindme.data.db.NoteDatabase;
import pfe.remindme.data.db.TagDatabase;
import pfe.remindme.data.entity.NoteEntity;
import pfe.remindme.data.entity.TagEntity;

public class NoteDisplayLocalDataSource {
    private NoteDatabase noteDatabase;
    private TagDatabase tagDatabase;

    public NoteDisplayLocalDataSource(NoteDatabase noteDatabase, TagDatabase tagDatabase) {
        this.noteDatabase = noteDatabase;
        this.tagDatabase = tagDatabase;
    }

    public Single<NoteEntity> loadNote(int id) {
        return noteDatabase.noteDao().loadNote(id);
    }

    public Completable addNote(NoteEntity noteEntity) {
        return noteDatabase.noteDao().addNote(noteEntity);
    }

    public Completable removeNote(int noteId) {
        return noteDatabase.noteDao().deleteNote(noteId);
    }

    public Single<TagEntity> loadTag(String tagName) {
        return tagDatabase.tagDao().loadTag(tagName);
    }

    public Completable updateTag(TagEntity tagEntity) {
        return tagDatabase.tagDao().updateTag(tagEntity);
    }

    public Single<String> getAllTagsAsJson() {
        return tagDatabase.tagDao().getAllTags();
    }

    public Single<String> getTagsFromNoteAsJson(int id) {
        return noteDatabase.noteDao().getTagsFromNoteAsJson(id);
    }

    public Single<String> getLinkedNotesIdFromTagAsJson(String tagName) {
        return tagDatabase.tagDao().getLinkedNotesIdFromTagAsJson(tagName);
    }


}
