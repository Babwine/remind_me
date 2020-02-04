package pfe.remindme.presentation.notedisplay.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pfe.remindme.R;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {


    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView contentTextView;
        private TextView tagsTextView;
        private View v;
        private NoteItemViewModel noteItemViewModel;
        private NoteActionInterface noteActionInterface;
        private ImageButton deleteButton;

        public NoteViewHolder(@NonNull View v, NoteActionInterface noteActionInterface) {
            super(v);
            this.v = v;
            this.noteActionInterface = noteActionInterface;
            contentTextView = v.findViewById(R.id.note_content);
            tagsTextView = v.findViewById(R.id.note_tags);
            deleteButton = v.findViewById(R.id.deleteButton);

            setupListeners();
        }

        private void setupListeners() {
            deleteButton.setOnClickListener(new ImageButton.OnClickListener() {

                @Override
                public void onClick(View v) {
                    noteActionInterface.onNoteDeleted(noteItemViewModel.getId());
                }
            });
        }

        void bind(NoteItemViewModel noteItemViewModel) {
            this.noteItemViewModel = noteItemViewModel;
            contentTextView.setText(noteItemViewModel.getNoteContent());
            tagsTextView.setText(noteItemViewModel.getNoteTags());

        }
    }

    private List<NoteItemViewModel> noteItemViewModelList;
    private NoteActionInterface noteActionInterface;

    public NoteAdapter(NoteActionInterface noteActionInterface) {
        this.noteItemViewModelList = new ArrayList<>();
        this.noteActionInterface = noteActionInterface;
    }

    public void bindViewModels(List<NoteItemViewModel> noteItemViewModelList) {
        this.noteItemViewModelList.clear();
        this.noteItemViewModelList.addAll(noteItemViewModelList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        NoteViewHolder pokemonViewHolder = new NoteViewHolder(v,noteActionInterface);
        return pokemonViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(noteItemViewModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.noteItemViewModelList.size();
    }


}
