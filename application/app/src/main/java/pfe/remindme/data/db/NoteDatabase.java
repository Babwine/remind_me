package pfe.remindme.data.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import pfe.remindme.data.DataConverter;
import pfe.remindme.data.entity.NoteEntity;

/**
 * La base de données en charge de la gestion des notes
 */
@Database(entities = {NoteEntity.class}, version = 1, exportSchema = false)
@TypeConverters({DataConverter.class})
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
