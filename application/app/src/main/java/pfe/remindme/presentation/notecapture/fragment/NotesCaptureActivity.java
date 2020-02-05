package pfe.remindme.presentation.notecapture.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import pfe.remindme.R;
import pfe.remindme.data.Note;
import pfe.remindme.data.di.FakeDependencyInjection;
import pfe.remindme.data.repository.notedisplay.mapper.NoteEntityToNoteMapper;
import pfe.remindme.data.repository.notedisplay.mapper.NoteToNoteEntityMapper;
import pfe.remindme.presentation.notecapture.NoteCaptureContract;
import pfe.remindme.presentation.notecapture.NoteCapturePresenter;

public class NotesCaptureActivity extends AppCompatActivity implements NoteCaptureContract.View {

    protected static final int RESULT_SPEECH = 1;

    private EditText noteText;
    private Button ajouterButton;
    private Button clearButton;
    private FloatingActionButton backButton;
    private ImageButton micButton;
    private TextView lastAddedNoteText;
    private NoteCaptureContract.Presenter noteCapturePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        FakeDependencyInjection.setContext(this);

        noteText = findViewById(R.id.note_text);
        ajouterButton = findViewById(R.id.ajouter_note);
        clearButton = findViewById(R.id.clear_note);
        backButton = findViewById(R.id.back);
        micButton = findViewById(R.id.mic_button);
        lastAddedNoteText = findViewById(R.id.last_added_note);

        noteCapturePresenter = new NoteCapturePresenter(FakeDependencyInjection.getNoteDisplayRepository(), new NoteToNoteEntityMapper(), new NoteEntityToNoteMapper());
        noteCapturePresenter.attachView(this);
        noteCapturePresenter.getLastAddedNote();

        setupListeners();

    }

    private void setupListeners() {
        backButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ajouterButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                String note_content = noteText.getText().toString();
                if (note_content.length() != 0) {
                    noteCapturePresenter.addNote(note_content);
                    noteText.setText("");

                    lastAddedNoteText.setText(note_content);
                }
            }
        });

        clearButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                noteText.setText("");
            }
        });

        micButton.setOnClickListener(new ImageButton.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                try {
                    startActivityForResult(intent, RESULT_SPEECH);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onNoteAdded(Note note) {
        for (String tagName : note.getTags()) {
            noteCapturePresenter.getTag(tagName, note);
        }
    }

    @Override
    public void displayLastAddedNote(Note note) {
        lastAddedNoteText.setText(note.getContent());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_SPEECH:
                if (resultCode == RESULT_OK && data != null) {
                    List<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    noteText.setText(text.get(0));
                }
        }
    }
}
