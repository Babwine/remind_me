package pfe.remindme.presentation.notedisplay.adapter;

/**
 * Une classe pour servir de ViewModel pour une Note
 */
public class NoteItemViewModel {
    private String noteContent;
    private String noteTags;
    private int id;


    /**
     * Renvoie le contenu de la note
     * @return le contenu de la note
     */
    public String getNoteContent() {
        return noteContent;
    }

    /**
     * Initialise le contenu de la note avec la valeur <code>noteContent</code>
     * @param noteContent le contenu de la note donné
     */
    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    /**
     * Renvoie les tags associés à la note
     * @return les tags associés à la note
     */
    public String getNoteTags() {
        return noteTags;
    }

    /**
     * Initilalise la liste des tags de la ViewModel avec la valeur <code>noteTags</code>
     * @param noteTags la liste des tags sous forme de chaîne de caractères
     */
    public void setNoteTags(String noteTags) {
        this.noteTags = noteTags;
    }


    /**
     * Renvoie l'ID de la note associée à ce ViewModel
     * @return l'ID de la note
     */
    public int getId() {
        return id;
    }

    /**
     * Initialise l'ID de la note avec la valeur <code>id</code>
     * @param id l'ID de la note
     */
    public void setId(int id) {
        this.id = id;
    }
}
