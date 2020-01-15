package pfe.remindme.data.db;

import androidx.room.Database;

import pfe.remindme.data.entity.TagEntity;

@Database(entities = TagEntity.class, version = 1)
public abstract class TagDatabase {
    public abstract TagDao tagDao();
}
