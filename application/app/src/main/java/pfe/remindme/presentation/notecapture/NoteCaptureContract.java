package pfe.remindme.presentation.notecapture;

import java.util.List;

import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;
import pfe.remindme.presentation.notedisplay.adapter.NoteItemViewModel;

public interface NoteCaptureContract {
    interface View {

        void onNoteAdded(Note note);

        void displayLastAddedNote(Note note);
    }


    interface Presenter {
        void attachView(View view);

        void detachView();

        void addNote(String note_content);

        void updateTag(Tag tag);

        void addTag(Tag tag);

        void getTag(String tagName, Note note);

        void getLastAddedNote();

    }
}
