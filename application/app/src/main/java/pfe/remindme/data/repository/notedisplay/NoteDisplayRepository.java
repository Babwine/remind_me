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
    Single<NoteEntity> getNoteById(int noteId);

    Single<TagEntity> getTagByTagName(String tagName);

    Single<String> getTagDatabaseAsJson();

    Flowable<List<TagEntity>> getTagsFromNote(int noteId);

    Flowable<List<NoteEntity>> getLinkedNotesFromTag(String tagName);

    Completable addNote(Note note);

    Completable removeNote(int noteid);
}
