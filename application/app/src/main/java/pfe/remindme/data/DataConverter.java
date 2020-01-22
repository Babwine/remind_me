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
        Gson gson = new Gson();
        String json = gson.toJson(noteIdList);
        return json;
    }

    @TypeConverter
    public List<Integer> toNoteIdList(String noteIdString) {
        Type listType = new TypeToken<List<Integer>>() {}.getType();
        return new Gson().fromJson(noteIdString, listType);
    }

    @TypeConverter
    public String fromTagNameList(List<String> tagNameList) {
        Gson gson = new Gson();
        String json = gson.toJson(tagNameList);
        return json;
    }

    @TypeConverter
    public List<String> toTagNameList(String tagNameString) {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(tagNameString, listType);
    }
}