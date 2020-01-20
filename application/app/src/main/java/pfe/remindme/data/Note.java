package pfe.remindme.data;

import androidx.room.Ignore;

import java.util.ArrayList;
import java.util.List;

import pfe.remindme.data.repository.notedisplay.NoteDisplayRepository;

public class Note {
    int id;
    String content;
    List<String> tags;

    public Note(int id, String content) {
        this.id = id;
        this.content = content;
        this.tags = new ArrayList<>();
        String[] tmp = content.split(" ");
        for (String word : tmp) {
            tags.add(word);
        }
    }

    @Ignore
    public Note(String content) {
        this.content = content;
        this.tags = new ArrayList<>();
        String[] tmp = content.split(" ");
        for (String word : tmp) {
            tags.add(word);
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
