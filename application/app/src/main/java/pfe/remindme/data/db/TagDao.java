package pfe.remindme.data.db;

import androidx.room.Dao;
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

    @Update
    Completable updateTag(TagEntity tagEntity);

    @Query("SELECT * from tagentity")
    List<TagEntity> getAllTags();

    @Query("SELECT linkedNotesIdList from tagentity WHERE tagName = :tagName")
    Single<String> getLinkedNotesIdFromTagAsJson(String tagName);

}
