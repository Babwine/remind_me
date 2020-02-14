package pfe.remindme.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import pfe.remindme.data.DataConverter;

@Entity
public class TagEntity {
    /**
     * Le nom du tag
     */
    @NonNull
    @PrimaryKey
    String tagName;

    /**
     * La liste des ID des notes associées à cette TagEntity
     */
    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "linkedNotesIdList")
    List<Integer> linkedNotesIdList;

    /**
     * Renvoie la liste des ID des notes associées à cette TagEntity
     * @return la liste des ID des notes associées à cette TagEntity
     */
    @TypeConverters(DataConverter.class)
    public List<Integer> getLinkedNotesIdList() {
        return linkedNotesIdList;
    }

    /**
     * Modifie la liste des ID des notes associées au tag en l'initialisant à <code>linkedNotesIdList</code>
     * @param linkedNotesIdList la liste donnée
     */
    public void setLinkedNotesIdList(List<Integer> linkedNotesIdList) {
        this.linkedNotesIdList = linkedNotesIdList;
    }

    /**
     * Renvoie le nom de cette TagEntity
     * @return le nom de cette TagEntity
     */
    @NonNull
    public String getTagName() {
        return tagName;
    }

    /**
     * Modifie le nom du tag en l'initialisant à <code>tagName</code>
     * @param tagName le nom donné
     */
    public void setTagName(@NonNull String tagName) {
        this.tagName = tagName;
    }
}
