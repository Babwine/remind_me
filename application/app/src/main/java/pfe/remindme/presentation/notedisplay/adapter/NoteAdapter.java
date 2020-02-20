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

/**
 * Une classe servant d'adapteur pour les NoteItemViewModels
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    /**
     * Une classe pour le ViewHolder associé
     */
    public class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView contentTextView;
        private TextView tagsTextView;
        private View v;
        private NoteItemViewModel noteItemViewModel;
        private NoteActionInterface noteActionInterface;
        private ImageButton deleteButton;

        /**
         * Constructeur
         * @param v la vue
         * @param noteActionInterface la NoteActionInterface
         */
        public NoteViewHolder(@NonNull View v, NoteActionInterface noteActionInterface) {
            super(v);
            this.v = v;
            this.noteActionInterface = noteActionInterface;
            contentTextView = v.findViewById(R.id.note_content);
            tagsTextView = v.findViewById(R.id.note_tags);
            deleteButton = v.findViewById(R.id.deleteButton);

            setupListeners();
        }

        /**
         * Une méthode qui met en place les listeners pour les actions sur chaque note
         */
        private void setupListeners() {
            deleteButton.setOnClickListener(new ImageButton.OnClickListener() {

                @Override
                public void onClick(View v) {
                    noteActionInterface.onNoteDeleted(noteItemViewModel.getId());
                }
            });
        }

        /**
         * Lie le ViewModel <code>noteItemViewModel</code> à la vue de la Note
         * @param noteItemViewModel le ViewModel
         */
        void bind(NoteItemViewModel noteItemViewModel) {
            this.noteItemViewModel = noteItemViewModel;
            contentTextView.setText(noteItemViewModel.getNoteContent());
            tagsTextView.setText(noteItemViewModel.getNoteTags());

        }
    }

    private List<NoteItemViewModel> noteItemViewModelList;
    private NoteActionInterface noteActionInterface;

    /**
     * Constructeur
     * @param noteActionInterface la NoteActionInterface
     */
    public NoteAdapter(NoteActionInterface noteActionInterface) {
        this.noteItemViewModelList = new ArrayList<>();
        this.noteActionInterface = noteActionInterface;
    }

    /**
     * Lie les ViewModel de la liste <code>noteItemViewModelList</code> à la recyclerView
     * @param noteItemViewModelList la liste de ViewModel
     */
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
