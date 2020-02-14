package pfe.remindme.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import pfe.remindme.data.entity.NoteEntity;

/**
 * Le DAO de la BDD des notes
 */
@Dao
public interface NoteDao {
    /**
     * Requête permettant de récupérer une note via son <code>id</code>
     * @param id l'ID de la note
     * @return la NoteEntity correspondant à la note, enveloppée dans un observable RxJava
     */
    @Query("SELECT * from noteentity WHERE id = :id")
    Single<NoteEntity> loadNote(int id);

    /**
     * Requête permettant d'ajouter la note correspondant à <code>noteEntity</code> dans la base de données des notes
     * @param noteEntity la NoteEntity stockée dans la base
     * @return l'ID de la note ajoutée enveloppée dans un observable RxJava
     */
    @Insert
    Single<Long> addNote(NoteEntity noteEntity);

    /**
     * Requête permettant de supprimer toutes les notes de la base de données des notes
     * <bold>Cette fonctionnalité n'est pas utilisable actuellement depuis l'application</bold>
     * @return une confirmation de la suppression sous la forme d'un observable RxJava
     */
    @Query("DELETE FROM noteentity")
    Completable deleteAll();

    /**
     * Requête permettant de supprimer la note d'ID <code>id</code> de la base de données des notes
     * @param id l'ID de la note à supprimer
     * @return une confirmation de la suppression sous la forme d'un observable RxJava
     */
    @Query("DELETE FROM noteentity WHERE id = :id")
    Completable deleteNote(int id);

    /**
     * Requête permettant de récupérer la liste des tags associés à la note d'ID <code>id</code> sous la forme de la chaîne de caractères JSON stockée dans la base
     * @param id l'ID de la note
     * @return la chaîne de caractères JSON demandée enveloppée dans un observable RxJava
     */
    @Query("SELECT tagList from noteentity WHERE id = :id")
    Single<String> getTagsFromNoteAsJson(int id);

    /**
     * Requête permettant de récupérer la liste de toutes les notes de la base de données des notes dans l'ordre de la plus récente à la plus ancienne, en fonction de l'ID
     * @return la liste de toutes les notes enveloppée dans un observable RxJava
     */
    @Query("SELECT * from noteentity ORDER BY id DESC")
    Flowable<List<NoteEntity>> loadAllNotes();

    /**
     * Requête permettant de récupérer la liste de toutes les notes dont l'ID est contenu dans la liste <code>noteIdList</code>
     * @param noteIdList la liste des ID des notes à récupérer
     * @return la liste des notes concernées enveloppée dans un observable RxJava
     */
    @Query("SELECT * from noteentity WHERE id IN (:noteIdList) ORDER BY id DESC")
    Flowable<List<NoteEntity>> loadNotesFromIdList(List<Integer> noteIdList);

    /**
     * Requête permettant de récupérer la dernière note ajoutée dans la base de données des notes
     * @return la note concernée enveloppée dans un observable RxJava
     */
    @Query("SELECT * from noteentity WHERE id = (SELECT max(id) FROM noteentity)")
    Single<NoteEntity> loadLastAddedNote();
}
