package pfe.remindme.data;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    private String tagName;
    private List<Note> linkedNotes;

    public Tag(String tagName) {
        this.tagName = tagName;
        this.linkedNotes = new ArrayList<>();
    }

    public void addNote(Note n) {
        linkedNotes.add(n);
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Note> getLinkedNotes() {
        return linkedNotes;
    }

    public void setLinkedNotes(List<Note> linkedNotes) {
        this.linkedNotes = linkedNotes;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Tag) {
            if (((Tag) other).getTagName().equals(this.getTagName())) {
                return true;
            } else return false;
        } else return false;
    }
}
