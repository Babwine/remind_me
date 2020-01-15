package pfe.remindme.data;

import java.util.ArrayList;
import java.util.List;

public class Note {
    public static int last_id;
    int id;
    String content;
    List<Tag> tags;

    public Note(String content, List<Tag> tagDatabase) { //TODO TMP
        this.id = last_id;
        last_id++;
        this.content = content;
        this.tags = new ArrayList<>();
        String[] tmp = content.split(" ");
        for (String word : tmp) {
            tags.add(new Tag(word));
        }
        updateTags(tagDatabase);
    }


    private void updateTags(List<Tag> tagDatabase) {
        for (Tag t : tags) {
            t.addNote(this);
            if (tagDatabase.contains(t)) {
                for (Tag tInDB : tagDatabase) {
                    if (t.equals(tInDB)) {
                        tInDB.addNote(this);
                        for (Note n : tInDB.getLinkedNotes()) {
                            if (!n.equals(this)) {
                                t.addNote(n);
                            }
                        }
                    }
                }
            } else {
                tagDatabase.add(t);
            }
        }
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Note) {
            if (((Note) other).getContent().equals(this.getContent())) {
                return true;
            } else return false;
        } else return false;
    }
}
