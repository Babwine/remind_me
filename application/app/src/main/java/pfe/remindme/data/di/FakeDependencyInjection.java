package pfe.remindme.data.di;

import pfe.remindme.data.repository.notedisplay.NoteDisplayDataRepository;
import pfe.remindme.data.repository.notedisplay.NoteDisplayRepository;

public class FakeDependencyInjection {
    public static NoteDisplayRepository getPokemonDisplayRepository() {
        return new NoteDisplayDataRepository(); // TODO TMP
    }
}
