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
    @NonNull
    @PrimaryKey
    String tagName;

    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "linkedNotesIdList")
    List<Integer> linkedNotesIdList;


    @TypeConverters(DataConverter.class)
    public List<Integer> getLinkedNotesIdList() {
        return linkedNotesIdList;
    }

    public void setLinkedNotesIdList(List<Integer> linkedNotesIdList) {
        this.linkedNotesIdList = linkedNotesIdList;
    }

    @NonNull
    public String getTagName() {
        return tagName;
    }

    public void setTagName(@NonNull String tagName) {
        this.tagName = tagName;
    }
}
