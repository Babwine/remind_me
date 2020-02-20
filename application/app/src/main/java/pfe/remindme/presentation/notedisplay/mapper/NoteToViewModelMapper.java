package pfe.remindme.presentation.notedisplay.mapper;

import java.util.ArrayList;
import java.util.List;

import pfe.remindme.data.Note;
import pfe.remindme.presentation.notedisplay.adapter.NoteItemViewModel;

/**
 * Le mapper qui transforme une Note en NoteItemViewModel
 */
public class NoteToViewModelMapper {

    /**
     * Transforme la note <code>note</code> en NoteItemViewModel
     * @param note la note
     * @return un NoteItemViewModel correspondant à la note
     */
    public NoteItemViewModel map(Note note) {
        NoteItemViewModel noteItemViewModel = new NoteItemViewModel();
        noteItemViewModel.setId(note.getId());
        noteItemViewModel.setNoteContent(note.getContent());
        String tagsString = "";
        for (String t : note.getTags()) {
            tagsString += t + ", ";
        }
        tagsString = tagsString.substring(0,tagsString.length()-2);


        noteItemViewModel.setNoteTags(tagsString);

        return noteItemViewModel;
    }

    /**
     * Transforme la liste de notes <code>noteList</code> en liste de NoteItemViewModel
     * @param noteList la liste de notes
     * @return une liste de NoteItemViewModel correspondants aux notes de la liste susmentionnée
     */
    public List<NoteItemViewModel> map(List<Note> noteList) {
        List<NoteItemViewModel> noteItemViewModelList = new ArrayList<>();
        for (Note note : noteList) {
            noteItemViewModelList.add(this.map(note));
        }

        return noteItemViewModelList;
    }
}
