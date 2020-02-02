package pfe.remindme.data.repository.notedisplay;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;
import pfe.remindme.data.entity.NoteEntity;
import pfe.remindme.data.entity.TagEntity;

public interface NoteDisplayRepository {
    Single<Note> getNoteById(int noteId);

    Flowable<List<Note>> getAllNotes();

    Single<Tag> getTagByTagName(String tagName);

    Flowable<List<Tag>> getTagDatabase();

    Single<String> getTagsFromNoteAsJson(int noteId);

    Single<String> getLinkedNotesIdFromTagAsJson(String tagName);

    Flowable<List<String>> getLinkedNotesIdFromStringLikeTagAsJson(String str);

    Single<Long> addNote(NoteEntity noteEntity);

    Completable updateTag(Tag tag);

    Completable removeNote(int noteid);

    Flowable<List<Note>> getNotesFromIdList(List<Integer> noteIdList);

    Completable deleteAllNotes();

    Completable deleteAllTags();

    Completable addTag(Tag tag);
}
