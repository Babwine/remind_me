package pfe.remindme.data.repository.notedisplay.mapper;

import java.util.ArrayList;
import java.util.List;

import pfe.remindme.data.Note;
import pfe.remindme.data.entity.NoteEntity;

/**
 * La classe pour le mapper qui transforme une NoteEntity en Note
 */
public class NoteEntityToNoteMapper {
    /**
     * Transforme la NoteEntity <code>noteEntity</code> en Note
     * @param noteEntity la NoteEntity donnée
     * @return la Note obtenue à partir de la NoteEntity donnée
     */
    public Note map(NoteEntity noteEntity) {
        return new Note(noteEntity.getId(), noteEntity.getContent(), noteEntity.getTagList());
    }

    /**
     * Transforme la liste de NoteEntities <code>noteEntityList</code> en liste de Notes
     * @param noteEntityList la liste de NoteEntities donnée
     * @return la liste de Notes obtenue à partir de la liste de NoteEntities donnée
     */
    public List<Note> map(List<NoteEntity> noteEntityList) {
        List<Note> noteList = new ArrayList<>();
        for (NoteEntity noteEntity : noteEntityList) {
            noteList.add(map(noteEntity));
        }
        return noteList;
    }
}
