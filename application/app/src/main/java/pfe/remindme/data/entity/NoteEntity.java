package pfe.remindme.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import pfe.remindme.data.DataConverter;

@Entity
public class NoteEntity {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String content;
    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "tagList")
    List<String> tagList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
