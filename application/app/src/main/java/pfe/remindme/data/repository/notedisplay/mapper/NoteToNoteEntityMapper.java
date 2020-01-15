package pfe.remindme.data.repository.notedisplay.mapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;
import pfe.remindme.data.entity.NoteEntity;

public class NoteToNoteEntityMapper {
    public NoteEntity map(Note note) {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setId(note.getId());
        noteEntity.setContent(note.getContent());

        List<String> tagList = new ArrayList<>();
        for (Tag tag : note.getTags()) {
            tagList.add(tag.getTagName())
;        }
        noteEntity.setTagList(tagList);

        return noteEntity;
    }
}
