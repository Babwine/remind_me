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

        void onNoteAdded(Note note);

        void onNoteDeleted();



        void getTag(Tag tag);

    }


    interface Presenter {
        void attachView(View view);

        void detachView();

        void displayAllNotes();

        void addNote(String note_content);

        void removeNote(int noteId);

        void displayNotesFromTag(String tagName);

        void displayNotesFromIdList(List<Integer> noteIdList);

        void deleteAllNotes();

        void deleteAllTags();

        void getTag(String tagName);

        void updateTag(Tag tag);

        void addTag(Tag tag);

        void getTagDatabase();
    }
}
