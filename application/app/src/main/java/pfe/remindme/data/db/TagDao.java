package pfe.remindme.data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import pfe.remindme.data.Tag;
import pfe.remindme.data.entity.TagEntity;

@Dao
public interface TagDao {
    @Query("SELECT * from tagentity WHERE tagName = :tagName")
    Single<TagEntity> loadTag(String tagName);

    @Insert
    Completable addTag(TagEntity tagEntity);

    @Update
    Completable updateTag(TagEntity tagEntity);

    @Query("DELETE FROM tagentity")
    Completable deleteAll();

    @Query("SELECT * from tagentity")
    Flowable<List<TagEntity>> getAllTags();

    @Query("SELECT linkedNotesIdList from tagentity WHERE tagName = :tagName")
    Single<String> getLinkedNotesIdFromTagAsJson(String tagName);

    @Query("SELECT linkedNotesIdList from tagentity WHERE tagName LIKE '%' || :str || '%'")
    Flowable<List<String>> getLinkedNotesIdFromStringLikeTagAsJson(String str);

}
