package pfe.remindme.data;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


/**
 * Une classe pour permettre la conversion de liste d'ID de notes et de noms
 * de tags en chaînes de caractères JSON et vice-versa
 */
public class DataConverter {

    /**
     * Convertit la liste d'ID de notes <code>noteIdList</code> en chaîne de caractères JSON
     * @param noteIdList la liste d'ID de notes donnée
     * @return la chaîne de caractères JSON correspondante
     */
    @TypeConverter
    public String fromNoteIdList(List<Integer> noteIdList) {
        Gson gson = new Gson();
        String json = gson.toJson(noteIdList);
        return json;
    }

    /**
     * Convertit la chaîne de caractères JSON <code>noteIdString</code> en la liste d'ID de notes correspondante
     * @param noteIdString la chaîne de caractères JSON donnée
     * @return la liste d'ID de notes correspondante
     */
    @TypeConverter
    public List<Integer> toNoteIdList(String noteIdString) {
        Type listType = new TypeToken<List<Integer>>() {}.getType();
        return new Gson().fromJson(noteIdString, listType);
    }

    /**
     * Convertit la liste de noms de tags <code>tagNameList</code> en chaîne de caractères JSON
     * @param tagNameList la liste de noms de tags donnée
     * @return la chaîne de caractères JSON correspondante
     */
    @TypeConverter
    public String fromTagNameList(List<String> tagNameList) {
        Gson gson = new Gson();
        String json = gson.toJson(tagNameList);
        return json;
    }

    /**
     * Convertit la chaîne de caractères JSON <code>tagNameString</code> en la liste de noms de tags correspondante
     * @param tagNameString la chaîne de caractères JSON donnée
     * @return la liste de noms de tags correspondante
     */
    @TypeConverter
    public List<String> toTagNameList(String tagNameString) {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(tagNameString, listType);
    }
}