package pfe.remindme.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import pfe.remindme.data.entity.TagEntity;

@Database(entities = { TagEntity.class }, version = 1, exportSchema = false)
public abstract class TagDatabase extends RoomDatabase {
    public abstract TagDao tagDao();
}
