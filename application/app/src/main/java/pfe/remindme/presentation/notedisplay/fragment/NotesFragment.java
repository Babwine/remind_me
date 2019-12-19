package pfe.remindme.presentation.notedisplay.fragment;

import androidx.fragment.app.Fragment;

import java.util.List;

import pfe.remindme.presentation.notedisplay.NoteContract;
import pfe.remindme.presentation.notedisplay.adapter.NoteActionInterface;
import pfe.remindme.presentation.notedisplay.adapter.NoteItemViewModel;


public class NotesFragment extends Fragment implements NoteActionInterface, NoteContract.View {


    @Override
    public void displayNotes(List<NoteItemViewModel> noteItemViewModelList) {

    }
}
