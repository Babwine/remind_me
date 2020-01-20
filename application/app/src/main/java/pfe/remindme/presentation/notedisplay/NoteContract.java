package pfe.remindme.presentation.notedisplay;

import java.util.List;

import pfe.remindme.data.Note;
import pfe.remindme.presentation.notedisplay.adapter.NoteItemViewModel;

public interface NoteContract {
    interface View {
        void displayNotes(List<NoteItemViewModel> noteItemViewModelList);

        void onNoteAdded();

        void onNoteDeleted();
    }


    interface Presenter {
        void attachView(View view);

        void detachView();

        void displayNotes();

        void addNote(String note_content);

        void removeNote(int noteId);
    }
}
