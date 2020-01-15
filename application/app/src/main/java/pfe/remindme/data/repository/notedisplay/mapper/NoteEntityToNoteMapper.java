package pfe.remindme.data.repository.notedisplay.mapper;

import java.util.List;

import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;
import pfe.remindme.data.entity.NoteEntity;

public class NoteEntityToNoteMapper {
    public Note map(NoteEntity noteEntity) {
        return new Note(noteEntity.getId(), noteEntity.getContent());
    }
}
