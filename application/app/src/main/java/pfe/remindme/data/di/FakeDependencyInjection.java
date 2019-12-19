package pfe.remindme.data.di;

import pfe.remindme.data.repository.NoteDisplayDataRepository;
import pfe.remindme.data.repository.NoteDisplayRepository;

public class FakeDependencyInjection {
    public static NoteDisplayRepository getPokemonDisplayRepository() {
        return new NoteDisplayDataRepository(); // TODO TMP
    }
}
