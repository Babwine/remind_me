package pfe.remindme.data.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import pfe.remindme.data.entity.NoteEntity;

@Database(entities = {NoteEntity.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
