package pfe.remindme.presentation.notedisplay;

import java.util.List;

import pfe.remindme.NotesDisplayActivity;
import pfe.remindme.data.Note;
import pfe.remindme.presentation.notedisplay.adapter.NoteItemViewModel;

public interface NoteContract {
    interface View {
        void displayNotes(List<NoteItemViewModel> noteItemViewModelList);
    }
    interface Presenter {
        void attachView(View view);

        void detachView();

        void displayNotes(List<Note> theNotes);
    }
}
