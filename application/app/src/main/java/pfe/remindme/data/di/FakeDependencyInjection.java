package pfe.remindme.data.di;

import android.content.Context;

import androidx.room.Room;

import pfe.remindme.data.db.NoteDatabase;
import pfe.remindme.data.db.TagDatabase;
import pfe.remindme.data.repository.notedisplay.NoteDisplayDataRepository;
import pfe.remindme.data.repository.notedisplay.NoteDisplayRepository;
import pfe.remindme.data.repository.notedisplay.local.NoteDisplayLocalDataSource;
import pfe.remindme.data.repository.notedisplay.mapper.NoteEntityToNoteMapper;
import pfe.remindme.data.repository.notedisplay.mapper.NoteToNoteEntityMapper;
import pfe.remindme.data.repository.notedisplay.mapper.TagEntityToTagMapper;
import pfe.remindme.data.repository.notedisplay.mapper.TagToTagEntityMapper;
import pfe.remindme.data.repository.notedisplay.remote.NoteDisplayRemoteDataSource;

/**
 * La classe implémentée en l'absence d'une injection de dépendances plus poussée (cf utilisation de Dagger)
 */
public class FakeDependencyInjection {
    private static NoteDisplayRepository noteDisplayRepository;
    private static NoteDatabase noteDatabase;
    private static TagDatabase tagDatabase;
    private static Context applicationContext;

    /**
     * Initialise si besoin et renvoie le repository de l'application
     * @return le repository
     */
    public static NoteDisplayRepository getNoteDisplayRepository() {
        if (noteDisplayRepository == null) {
            noteDisplayRepository = new NoteDisplayDataRepository(
                    new NoteDisplayLocalDataSource(getNoteDatabase(), getTagDatabase()),
                    new NoteDisplayRemoteDataSource(),
                    new NoteToNoteEntityMapper(),
                    new TagToTagEntityMapper(),
                    new NoteEntityToNoteMapper(),
                    new TagEntityToTagMapper()
            );
        }
        return noteDisplayRepository;
    }

    /**
     * Initialise si besoin et renvoie la base de données des notes de l'application
     * @return la BDD des notes
     */
    public static NoteDatabase getNoteDatabase() {
        if (noteDatabase == null) {
            noteDatabase = Room.databaseBuilder(applicationContext,
                    NoteDatabase.class, "note-database").build();

        }
        return noteDatabase;
    }

    /**
     * Initialise si besoin et renvoie la base de données des tags de l'application
     * @return la BDD des tags
     */
    public static TagDatabase getTagDatabase() {
        if (tagDatabase == null) {
            tagDatabase = Room.databaseBuilder(applicationContext,
                    TagDatabase.class, "tag-database").build();

        }
        return tagDatabase;
    }

    /**
     * Initialise le contexte de l'application
     * @param context le contexte d'initialisation
     */
    public static void setContext(Context context) {
        applicationContext = context;
    }





}
