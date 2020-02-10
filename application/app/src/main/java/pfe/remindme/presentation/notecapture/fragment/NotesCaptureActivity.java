package pfe.remindme.presentation.notecapture.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
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
import pfe.remindme.presentation.notedisplay.fragment.NotesDisplayActivity;

public class NotesCaptureActivity extends AppCompatActivity implements NoteCaptureContract.View {

    protected static final int RESULT_SPEECH = 1;

    private EditText noteText;
    private Button ajouterButton;
    private Button clearButton;
    private FloatingActionButton searchButton;
    private ImageButton micButton;
    private Switch lockButton;
    private TextView lastAddedNoteText;
    private NoteCaptureContract.Presenter noteCapturePresenter;

    private boolean lockedCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        FakeDependencyInjection.setContext(this);

        noteText = findViewById(R.id.note_text);
        ajouterButton = findViewById(R.id.ajouter_note);
        clearButton = findViewById(R.id.clear_note);
        searchButton = findViewById(R.id.search);
        micButton = findViewById(R.id.mic_button);
        lastAddedNoteText = findViewById(R.id.last_added_note);
        lockButton = findViewById(R.id.lock_button);

        SharedPreferences sharedPreferences = getSharedPreferences("pfe.remindme", MODE_PRIVATE);
        lockButton.setChecked(sharedPreferences.getBoolean("isLockedCapture", true));
        lockedCapture = sharedPreferences.getBoolean("isLockedCapture", true);

        noteCapturePresenter = new NoteCapturePresenter(FakeDependencyInjection.getNoteDisplayRepository(), new NoteToNoteEntityMapper(), new NoteEntityToNoteMapper());
        noteCapturePresenter.attachView(this);
        noteCapturePresenter.getLastAddedNote();

        setupListeners();

    }

    private void switchActivity(View v) {
        Intent intent = new Intent(this, NotesDisplayActivity.class);
        startActivity(intent);
    }

    private void setupListeners() {
        searchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchActivity(v);
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

        lockButton.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onLockedCaptureToggled(isChecked);
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
    public void onLockedCaptureToggled(boolean isChecked) {
        lockedCapture = isChecked;
        SharedPreferences.Editor editor = getSharedPreferences("pfe.remindme", MODE_PRIVATE).edit();
        editor.putBoolean("isLockedCapture",isChecked);
        editor.commit();
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
                    if (lockedCapture) {
                        noteCapturePresenter.addNote(text.get(0));
                        lastAddedNoteText.setText(text.get(0));
                    } else {
                        noteText.setText(text.get(0));
                    }
                }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
