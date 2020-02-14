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

/**
 * Le DAO de la BDD des tags
 */
@Dao
public interface TagDao {
    /**
     * Requête permettant de récupérer un tag à partir de son nom <code>tagName</code>
     * @param tagName le nom du tag
     * @return l'entité correspondant au tag enveloppée dans un observable RxJava
     */
    @Query("SELECT * from tagentity WHERE tagName = :tagName")
    Single<TagEntity> loadTag(String tagName);

    /**
     * Requête permettant d'ajouter le tag <code>tagEntity</code> à la base de données des tags
     * @param tagEntity l'entité du tag à ajouter
     * @return une confirmation sous la forme d'un observable RxJava
     */
    @Insert
    Completable addTag(TagEntity tagEntity);

    /**
     * Requête permettant de mettre à jour les données du tag <code>tagEntity</code> si celui-ci existe déjà dans la base de données des tags
     * @param tagEntity l'entité du tag à modifier
     * @return une confirmation sous la forme d'un observable RxJava
     */
    @Update
    Completable updateTag(TagEntity tagEntity);

    /**
     * Requête permettant de supprimer tous les tags de la base de données des tags
     * <bold>Cette fonctionnalité n'est pas utilisable actuellement depuis l'application</bold>
     * @return une confirmation sous la forme d'un observable RxJava
     */
    @Query("DELETE FROM tagentity")
    Completable deleteAll();

    /**
     * Requête permettant de récupérer tous les tags de la base de données des tags
     * <bold>Cette fonctionnalité n'est pas utilisable actuellement depuis l'application</bold>
     * @return la liste de tous les tags enveloppée dans un observable RxJava
     */
    @Query("SELECT * from tagentity")
    Flowable<List<TagEntity>> getAllTags();

    /**
     * Requête permettant de récupérer la liste des notes associées au tag dont le nom est <code>tagName</code> sous la forme d'une chaîne de caractères JSON stockée dans la base
     * @param tagName le nom du tag
     * @return la chaîne de caractères JSON demandée enveloppée dans un observable RxJava
     */
    @Query("SELECT linkedNotesIdList from tagentity WHERE tagName = :tagName")
    Single<String> getLinkedNotesIdFromTagAsJson(String tagName);

    /**
     * Requête permettant de récupérer la liste des chaînes de caractères JSON correspondant aux listes de notes associées à tous les tags contenant la chaîne de caractères <code>str</code>
     * @param str la chaîne de caractères donnée
     * @return la liste de chaîne de caractères JSON demandée enveloppée dans un observable RxJava
     */
    @Query("SELECT linkedNotesIdList from tagentity WHERE tagName LIKE '%' || :str || '%'")
    Flowable<List<String>> getLinkedNotesIdFromStringLikeTagAsJson(String str);

}
