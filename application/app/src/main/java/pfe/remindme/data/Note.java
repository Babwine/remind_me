package pfe.remindme.data;

import java.util.ArrayList;
import java.util.List;

public class Note {
    public static int last_id;
    int id;
    String content;
    List<String> tags;

    public Note(String content, List<Tag> tagDatabase) { //TODO TMP
        this.id = last_id;
        last_id++;
        this.content = content;
        this.tags = new ArrayList<>();
        String[] tmp = content.split(" ");
        for (String word : tmp) {
            tags.add(word);
        }
        updateTags(tagDatabase);
    }

    public Note(int id, String content) { //TODO TMP
        this.id = id;
        this.content = content;
        this.tags = new ArrayList<>();
        String[] tmp = content.split(" ");
        for (String word : tmp) {
            tags.add(word);
        }
    }


    private void updateTags(List<Tag> tagDatabase) {
        for (String tag : tags) {
            Tag t = new Tag(tag);
            t.addNote(this.getId());
            if (tagDatabase.contains(t)) {
                for (Tag tInDB : tagDatabase) {
                    if (t.equals(tInDB)) {
                        tInDB.addNote(this.getId());
                        for (int n : tInDB.getLinkedNotes()) {
                            if (n != this.getId()) {
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
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
