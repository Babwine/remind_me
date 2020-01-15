package pfe.remindme.data;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import pfe.remindme.data.entity.NoteEntity;

public class DataConverter {

    @TypeConverter
    public String fromNoteIdList(List<Integer> noteIdList) {
        if (noteIdList == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        String json = gson.toJson(noteIdList, type);
        return json;
    }

    @TypeConverter
    public List<Integer> toNoteIdList(String noteIdString) {
        if (noteIdString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Integer>>() {}.getType();
        List<Integer> noteIdList = gson.fromJson(noteIdString, type);
        return noteIdList;
    }
}