package pfe.remindme.data.repository.mapper;

import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;
import pfe.remindme.data.entity.NoteEntity;

public class NoteToNoteEntityMapper {
    public NoteEntity map(Note note) {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setId(note.getId());
        noteEntity.setContent(note.getContent());

        String tagString = "";
        for (Tag tag : note.getTags()) {
            tagString += tag.getTagName() + "|";
        }
        tagString = tagString.substring(0, tagString.length()-1);
        noteEntity.setTags(tagString);

        return noteEntity;
    }
}
