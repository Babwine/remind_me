package pfe.remindme.data;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    private String tagName;
    private List<Integer> linkedNotes;

    public Tag(String tagName) {
        this.tagName = tagName;
        this.linkedNotes = new ArrayList<>();
    }

    public Tag(String tagName, List<Integer> linkedNotes) {
        this.tagName = tagName;
        this.linkedNotes = linkedNotes;
    }

    public void addNote(int n) {
        linkedNotes.add(n);
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Integer> getLinkedNotes() {
        return linkedNotes;
    }

    public void setLinkedNotes(List<Integer> linkedNotes) {
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
