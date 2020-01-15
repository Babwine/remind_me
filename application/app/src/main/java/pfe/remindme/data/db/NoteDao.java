package pfe.remindme.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import io.reactivex.Completable;
import io.reactivex.Single;
import pfe.remindme.data.entity.NoteEntity;

@Dao
public interface NoteDao {
    @Query("SELECT * from noteentity WHERE id = :id")
    Single<NoteEntity> loadNote(int id);

    @Insert
    Completable addNote(NoteEntity noteEntity);

    @Query("DELETE FROM noteentity WHERE id = :id")
    Completable deleteNote(int id);
}
