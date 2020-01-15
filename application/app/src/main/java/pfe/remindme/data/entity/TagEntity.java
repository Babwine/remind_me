package pfe.remindme.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TagEntity {
    @NonNull
    @PrimaryKey
    String tagName;
    String linkedNotes;

    @NonNull
    public String getTagName() {
        return tagName;
    }

    public void setTagName(@NonNull String tagName) {
        this.tagName = tagName;
    }

    public String getLinkedNotes() {
        return linkedNotes;
    }

    public void setLinkedNotes(String linkedNotes) {
        this.linkedNotes = linkedNotes;
    }
}
