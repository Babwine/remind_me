package pfe.remindme.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import io.reactivex.Completable;
import io.reactivex.Single;
import pfe.remindme.data.entity.NoteEntity;
import pfe.remindme.data.entity.TagEntity;

@Dao
public interface TagDao {
    @Query("SELECT * from tagentity WHERE tagName = :tagName")
    Single<NoteEntity> loadTag(String tagName);

    @Insert
    Completable addTag(TagEntity tagEntity);

}
