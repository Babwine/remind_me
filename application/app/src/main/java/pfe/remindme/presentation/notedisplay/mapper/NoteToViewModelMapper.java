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
        noteItemViewModel.setNoteContent(note.getContent());
        String tagsString = "";
        for (Tag t : note.getTags()) {
            tagsString += t.getTagName() + ", ";
        }
        tagsString = tagsString.substring(0,tagsString.length()-1);

        tagsString+="\nLINKED NOTES :\n";
        for (Tag t : note.getTags()) {
            for (Note n : t.getLinkedNotes()) {
                if (!n.equals(note)) tagsString+=n.getContent()+"\n";
            }
        }

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
