package pfe.remindme.data;

import android.net.Uri;

import androidx.room.Ignore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pfe.remindme.R;
import pfe.remindme.data.repository.notedisplay.NoteDisplayRepository;

public class Note {
    int id;
    String content;
    List<String> tags;

    public Note(int id, String content, List<String> tagList) {
        this.id = id;
        this.content = content;
        this.tags = tagList;
    }

    @Ignore
    public Note(String content) {
        this.content = content;
        this.tags = new ArrayList<>();
        String[] tmp = content.split(" ");

        //List<String> inhibs = getInhibitedWordsList(".");
        //TEMPORAIRE
        List<String> inhibs = Arrays.asList("a alors c' car ce celle celles ces cette ceux d' dans de des donc du elle elles en et il ils j' je l' la le les leur lors lui ma mais me mes mon ni nous on or ou parce pour qu' que quel quelle qui sa se ses son sur ta te tes ton tu un une vous y à".split(" "));



        for (String word : tmp) {
            if (!inhibs.contains(word.toLowerCase()))
                tags.add(word);
        }
    }

    public static List<String> getInhibitedWordsList(String path) {
        List<String> res = new ArrayList<>();
        File file = new File(path);
        File[] tmpfile = file.listFiles();
        System.out.println("hi");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String[] tmp = br.readLine().split(" ");
            for (int i=0; i<tmp.length; i++) {
                tmp[i] = tmp[i].toLowerCase();
            }
            res = Arrays.asList(tmp);

        } catch (IOException e) {
            System.err.println(path);
            e.printStackTrace();
        }

        return res;
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
