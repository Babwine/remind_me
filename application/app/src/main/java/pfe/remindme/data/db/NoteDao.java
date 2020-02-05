package pfe.remindme.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import pfe.remindme.data.Note;
import pfe.remindme.data.entity.NoteEntity;

@Dao
public interface NoteDao {
    @Query("SELECT * from noteentity WHERE id = :id")
    Single<NoteEntity> loadNote(int id);

    @Insert
    Single<Long> addNote(NoteEntity noteEntity);

    @Query("DELETE FROM noteentity")
    Completable deleteAll();

    @Query("DELETE FROM noteentity WHERE id = :id")
    Completable deleteNote(int id);

    @Query("SELECT tagList from noteentity WHERE id = :id")
    Single<String> getTagsFromNoteAsJson(int id);

    @Query("SELECT * from noteentity ORDER BY id DESC")
    Flowable<List<NoteEntity>> loadAllNotes();

    @Query("SELECT * from noteentity WHERE id IN (:noteIdList)")
    Flowable<List<NoteEntity>> loadNotesFromIdList(List<Integer> noteIdList);

    @Query("SELECT * from noteentity WHERE id = (SELECT max(id) FROM noteentity)")
    Single<NoteEntity> loadLastAddedNote();
}
