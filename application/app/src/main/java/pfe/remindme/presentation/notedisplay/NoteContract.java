package pfe.remindme.presentation.notedisplay;

import java.util.List;

import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;
import pfe.remindme.presentation.notedisplay.adapter.NoteItemViewModel;

public interface NoteContract {
    interface View {
        void displayNotes();

        void setNotes(List<NoteItemViewModel> notes);

        void setNotesByIdList(List<Integer> noteIdList);

        void onNoteDeleted(final int noteId);
    }


    interface Presenter {
        void attachView(View view);

        void detachView();

        void displayAllNotes();

        void removeNote(int noteId);

        void displayNotesFromStringLikeTag(String str);

        void displayNotesFromIdList(List<Integer> noteIdList);

        void deleteAllNotes();

        void deleteAllTags();
    }
}
