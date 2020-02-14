package pfe.remindme.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import pfe.remindme.data.DataConverter;

/**
 * La classe représentant l'entité des notes qui sera le modèle des notes stockées en base de donnée
 */
@Entity
public class NoteEntity {
    /**
     * L'ID de la note
     */
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    /**
     * Le contenu de la note
     */
    public String content;

    /**
     * La liste des chaînes de caractères correspondant aux noms des tags associés à la note
     */
    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "tagList")
    List<String> tagList;


    /**
     * Renvoie l'ID de cette NoteEntity
     * @return l'ID de cette NoteEntity
     */
    public int getId() {
        return id;
    }

    /**
     * Modifie l'ID de la note en l'initialisant à <code>id</code>
     * @param id l'ID donné
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Renvoie le contenu de cette NoteEntity
     * @return le contenu de cette NoteEntit
     */
    public String getContent() {
        return content;
    }

    /**
     * Modifie le contenu de la note en l'initialisant à <code>content</code>
     * @param content le contenu donné
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Renvoie la liste des noms des tags associés à cette NoteEntity
     * @return la liste des noms des tags associés à cette NoteEntity
     */
    public List<String> getTagList() {
        return tagList;
    }

    /**
     * Modifie la liste des noms des tags associés à la note en l'initialisant à <code>tagList</code>
     * @param tagList la liste donnée
     */
    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
