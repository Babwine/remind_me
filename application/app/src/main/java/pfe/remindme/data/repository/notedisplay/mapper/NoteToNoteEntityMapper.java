package pfe.remindme.data.repository.notedisplay.mapper;

import pfe.remindme.data.Note;
import pfe.remindme.data.entity.NoteEntity;

/**
 * La classe pour le mapper qui transforme une Note en NoteEntity
 */
public class NoteToNoteEntityMapper {
    /**
     * Transforme la Note <code>note</code> en NoteEntity
     * @param note la Note donnée
     * @return la NoteEntity obtenue à partir de la Note donnée
     */
    public NoteEntity map(Note note) {
        NoteEntity noteEntity = new NoteEntity();
        if (note.getId() != 0) {
            noteEntity.setId(note.getId());
        }
        noteEntity.setContent(note.getContent());

        noteEntity.setTagList(note.getTags());

        return noteEntity;
    }
}
