package pfe.remindme.presentation.notedisplay.mapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;
import pfe.remindme.presentation.notedisplay.adapter.NoteItemViewModel;

public class NoteToViewModelMapper {

    public NoteItemViewModel map(Note note) {
        NoteItemViewModel noteItemViewModel = new NoteItemViewModel();
        noteItemViewModel.setId(note.getId());
        noteItemViewModel.setNoteContent(note.getContent());
        String tagsString = "";
        for (String t : note.getTags()) {
            tagsString += t + ", ";
        }
        tagsString = tagsString.substring(0,tagsString.length()-1);

        noteItemViewModel.setNoteTags(tagsString);

        return noteItemViewModel;
    }

    public List<NoteItemViewModel> map(List<Note> noteList) {
        List<NoteItemViewModel> noteItemViewModelList = new ArrayList<>();
        for (Note note : noteList) {
            noteItemViewModelList.add(this.map(note));
        }

        return noteItemViewModelList;
    }
}
