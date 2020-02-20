package pfe.remindme.presentation.notedisplay.adapter;

/**
 * Une interface qui est implémentée par les classes autorisant une action sur une note
 */
public interface NoteActionInterface {

    /**
     * Méthode déclenchée lors de la suppression de la note d'ID <code>noteId</code>
     * @param noteId l'ID de la note à supprimer
     */
    void onNoteDeleted(int noteId);

}
