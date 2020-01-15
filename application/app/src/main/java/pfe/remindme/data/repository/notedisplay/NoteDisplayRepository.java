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

    Single<Tag> getTagByTagName(String tagName);

    Flowable<List<Tag>> getTagDatabase();

    Single<String> getTagsFromNoteAsJson(int noteId);

    Single<String> getLinkedNotesIdFromTagAsJson(String tagName);

    Completable addNote(NoteEntity noteEntity);

    Completable updateTag(Tag tag);

    Completable removeNote(int noteid);
}
