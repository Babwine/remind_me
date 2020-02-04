package pfe.remindme.presentation.notecapture.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import pfe.remindme.R;
import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;
import pfe.remindme.data.di.FakeDependencyInjection;
import pfe.remindme.data.repository.notedisplay.mapper.NoteEntityToNoteMapper;
import pfe.remindme.data.repository.notedisplay.mapper.NoteToNoteEntityMapper;
import pfe.remindme.presentation.notecapture.NoteCaptureContract;
import pfe.remindme.presentation.notedisplay.NoteContract;
import pfe.remindme.presentation.notedisplay.NotePresenter;

public class NotesCaptureActivity extends AppCompatActivity implements NoteCaptureContract.View {

    private EditText noteText;
    private Button ajouterButton;
    private Button clearButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        FakeDependencyInjection.setContext(this);

        noteText = findViewById(R.id.note_text);
        ajouterButton = findViewById(R.id.ajouter_note);
        clearButton = findViewById(R.id.clear_note);
        backButton = findViewById(R.id.back);

        setupListeners();

    }

    private void setupListeners() {
        backButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
