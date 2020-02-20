package pfe.remindme.data.repository.notedisplay.local;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import pfe.remindme.data.db.NoteDatabase;
import pfe.remindme.data.db.TagDatabase;
import pfe.remindme.data.entity.NoteEntity;
import pfe.remindme.data.entity.TagEntity;

/**
 * La classe représentant la source de données locale de l'application, récupérable depuis le repository
 */
public class NoteDisplayLocalDataSource {
    private NoteDatabase noteDatabase;
    private TagDatabase tagDatabase;

    /**
     * Constructeur
     * @param noteDatabase la base de données des notes
     * @param tagDatabase la base de données des tags
     */
    public NoteDisplayLocalDataSource(NoteDatabase noteDatabase, TagDatabase tagDatabase) {
        this.noteDatabase = noteDatabase;
        this.tagDatabase = tagDatabase;
    }

    /**
     * Renvoie un observable RxJava contenant la note dont l'ID <code>id</code> est spécifié en paramètre
     * @param id l'ID de la note à récupérer
     * @return la NoteEntity correspondant à la note chercher enveloppée dans un observable RxJava
     */
    public Single<NoteEntity> loadNote(int id) {
        return noteDatabase.noteDao().loadNote(id);
    }

    /**
     * Ajoute la note représentée par <code>noteEntity</code> à la base de données des notes
     * @param noteEntity la NoteEntity représentant la note à ajouter
     * @return l'ID de la note ajoutée enveloppé dans un observable RxJava
     */
    public Single<Long> addNote(NoteEntity noteEntity) {
        return noteDatabase.noteDao().addNote(noteEntity);
    }

    /**
     * Supprime la note d'ID <code>noteId</code> de la base de données des notes
     * @param noteId l'ID de la note à supprimer
     * @return une confirmation sous la forme d'un observable RxJava
     */
    public Completable removeNote(int noteId) {
        return noteDatabase.noteDao().deleteNote(noteId);
    }

    /**
     * Renvoie le tag dont le nom est <code>tagName</code>
     * @param tagName le nom du tag
     * @return la TagEntity correspondant au tag demandé enveloppée dans un observable RxJava
     */
    public Single<TagEntity> loadTag(String tagName) {
        return tagDatabase.tagDao().loadTag(tagName);
    }

    /**
     * Met à jour les données du tag resprésenté par l'entité <code>tagEntity</code> et qui existe dans la BDD des tags
     * @param tagEntity la TagEntity correspondant au tag à modifier
     * @return une confirmation sous la forme d'un observable RxJava
     */
    public Completable updateTag(TagEntity tagEntity) {
        return tagDatabase.tagDao().updateTag(tagEntity);
    }

    /**
     * Renvoie la liste de tous les tags présents dans la base de données des tags
     * <bold>Cette fonctionnalité n'est pas utilisable actuellement depuis l'application</bold>
     * @return la liste de toutes les TagEntities correspondant aux tags enveloppée dans un observable RxJava
     */
    public Flowable<List<TagEntity>> getAllTags() {
        return tagDatabase.tagDao().getAllTags();
    }

    /**
     * Renvoie la liste des tags associés à la note d'ID <code>id</code> sous forme d'une chaîne de caractères JSON
     * @param id l'ID de la note
     * @return la liste de tags sous forme de chaîne de caractères JSON enveloppée dans un observable RxJava
     */
    public Single<String> getTagsFromNoteAsJson(int id) {
        return noteDatabase.noteDao().getTagsFromNoteAsJson(id);
    }

    /**
     * Renvoie la liste de toutes les notes associées au tag de nom <code>tagName</code> sous forme d'une chaîne de caractères JSON
     * @param tagName le nom du tag
     * @return la liste de notes sous forme d'une chaîne de caractères JSON enveloppée dans un observable RxJava
     */
    public Single<String> getLinkedNotesIdFromTagAsJson(String tagName) {
        return tagDatabase.tagDao().getLinkedNotesIdFromTagAsJson(tagName);
    }

    /**
     * Renvoie la liste de toutes les notes associées aux tags dont le nom contient la chaîne de caractères <code>str</code>, sous forme d'une chaîne de caractères JSON
     * @param str la chaîne de caractères recherchée
     * @return la liste de notes sous forme d'une chaîne de caractères JSON enveloppée dans un observable RxJava
     */
    public Flowable<List<String>> getLinkedNotesIdFromStringLikeTagAsJson(String str) {
        return tagDatabase.tagDao().getLinkedNotesIdFromStringLikeTagAsJson(str);
    }

    /**
     * Renvoie la liste de toutes les notes de la base de données des notes
     * @return la liste de toutes les notes enveloppée dans un observable RxJava
     */
    public Flowable<List<NoteEntity>> loadAllNotes() {
        return noteDatabase.noteDao().loadAllNotes();
    }

    /**
     * Renvoie la liste des notes dont les ID sont dans la liste <code>noteIdList</code>
     * @param noteIdList la liste d'ID des notes
     * @return la liste des notes demandée enveloppée dans un observable RxJava
     */
    public Flowable<List<NoteEntity>> getNotesFromIdList(List<Integer> noteIdList) {
        return noteDatabase.noteDao().loadNotesFromIdList(noteIdList);
    }

    /**
     * Supprime toutes les notes de la base de données des notes
     * <bold>Cette fonctionnalité n'est pas utilisable actuellement depuis l'application</bold>
     * @return une confirmation sous la forme d'un observable RxJava
     */
    public Completable deleteAllNotes() {
        return noteDatabase.noteDao().deleteAll();
    }

    /**
     * Supprime tous les tags de la base de données des tags
     * <bold>Cette fonctionnalité n'est pas utilisable actuellement depuis l'application</bold>
     * @return une confirmation sous la forme d'un observable RxJava
     */
    public Completable deleteAllTags() {
        return tagDatabase.tagDao().deleteAll();
    }

    /**
     * Ajoute le tag correspondant à <code>tagEntity</code> à la base de données des tags
     * @param tagEntity la TagEntity correspondant au tag à ajouter
     * @return une confirmation sous la forme d'un observable RxJava
     */
    public Completable addtag(TagEntity tagEntity) {
        return tagDatabase.tagDao().addTag(tagEntity);
    }

    /**
     * Renvoie la note la plus récemment ajoutée
     * @return la note en question enveloppée dans un observable RxJava
     */
    public Single<NoteEntity> loadLastAddedNote() {
        return noteDatabase.noteDao().loadLastAddedNote();
    }
}
