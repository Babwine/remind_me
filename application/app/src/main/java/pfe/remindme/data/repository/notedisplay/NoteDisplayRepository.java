package pfe.remindme.data.repository.notedisplay;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;
import pfe.remindme.data.entity.NoteEntity;

/**
 * L'interface correspondant au repository qui gère à la fois les notes et les tags
 */
public interface NoteDisplayRepository {
    /**
     * Renvoie la note d'ID <code>noteId</code>
     * <bold>Cette fonctionnalité n'est pas utilisée actuellement par l'application</bold>
     * @param noteId l'ID de la note recherchée
     * @return la note recherchée enveloppée dans un observable RxJava
     */
    Single<Note> getNoteById(int noteId);

    /**
     * Renvoie la liste de toutes les notes de la base de données des notes
     * @return la liste de toutes les notes enveloppée dans un observable RxJava
     */
    Flowable<List<Note>> getAllNotes();

    /**
     * Renvoie le tag dont le nom est <code>tagName</code>
     * @param tagName le nom du tag recherché
     * @return le tag recherché enveloppé dans un observable RxJava
     */
    Single<Tag> getTagByTagName(String tagName);

    /**
     * Renvoie la liste de tous les tags de la base de données des tags
     * <bold>Cette fonctionnalité n'est pas utilisée actuellement par l'application</bold>
     * @return la liste de tous les tags enveloppée dans un observable RxJava
     */
    Flowable<List<Tag>> getTagDatabase();

    /**
     * Renvoie la liste de tous les tags associés à la note d'ID <code>noteId</code> sous forme de chaîne de caractères JSON
     * <bold>Cette fonctionnalité n'est pas utilisée actuellement par l'application</bold>
     * @param noteId l'ID de la note concernée
     * @return la liste des tags sous forme de chaîne de caractères JSON enveloppée dans un observable RxJava
     */
    Single<String> getTagsFromNoteAsJson(int noteId);

    /**
     * Renvoie la liste des notes associées au tag de nom <code>tagName</code> sous forme de chaîne de caractères JSON
     * <bold>Cette fonctionnalité n'est pas utilisée actuellement par l'application</bold>
     * @param tagName le nom du tag
     * @return la liste des notes sous forme de chaîne de caractères JSON enveloppée dans un observable RxJava
     */
    Single<String> getLinkedNotesIdFromTagAsJson(String tagName);

    /**
     * Renvoie la liste des notes associées aux tags dont le nom contient la chaîne de caractères <code>str</code> sous forme de chaîne de caractères JSON
     * @param str la chaîne de caractères recherchée
     * @return la liste des notes sous forme de chaîne de caractères JSON enveloppée dans un observable RxJava
     */
    Flowable<List<String>> getLinkedNotesIdFromStringLikeTagAsJson(String str);

    /**
     * Ajoute la note correspondant à l'entité <code>noteEntity</code> au repository
     * @param noteEntity l'entité correspondant à la note
     * @return l'ID de la note ajoutée enveloppée dans un observable RxJava
     */
    Single<Long> addNote(NoteEntity noteEntity);

    /**
     * Met à jour le tag correspondant au Tag <code>tag</code> s'il existe dans le repository
     * @param tag le tag à mettre à jour
     * @return une confirmation sous la forme d'un observable RxJava
     */
    Completable updateTag(Tag tag);

    /**
     * Supprime du repository la note d'ID <code>noteId</code>
     * @param noteid l'ID de la note à supprimer
     * @return une confirmation sous la forme d'un observable RxJava
     */
    Completable removeNote(int noteid);

    /**
     * Renvoie la liste des notes dont les ID sont dans la liste <code>noteIdList</code>
     * @param noteIdList la liste des ID des notes recherchées
     * @return la liste des notes recherchées enveloppée dans un observable RxJava
     */
    Flowable<List<Note>> getNotesFromIdList(List<Integer> noteIdList);

    /**
     * Supprime toutes les notes du repository
     * <bold>Cette fonctionnalité n'est pas utilisable actuellement par l'application</bold>
     * @return une confirmation sous la forme d'un observable RxJava
     */
    Completable deleteAllNotes();

    /**
     * Supprime tous les tags du repository
     * <bold>Cette fonctionnalité n'est pas utilisable actuellement par l'application</bold>
     * @return une confirmation sous la forme d'un observable RxJava
     */
    Completable deleteAllTags();

    /**
     * Ajoute le tag <code>tag</code> au repository
     * @param tag le tag à ajouter
     * @return une confirmation sous la forme d'un observable RxJava
     */
    Completable addTag(Tag tag);

    /**
     * Renvoie la dernière note ajoutée au repository
     * @return la note recherchée enveloppée dans un observable RxJava
     */
    Single<Note> getLastAddedNote();
}
