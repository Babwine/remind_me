package pfe.remindme.presentation.notecapture;


import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;

/**
 * L'interface faisant lieu de contrat entre la vue de capture des notes et son présenteur associé
 */
public interface NoteCaptureContract {
    /**
     * L'interface faisant lieu de vue
     */
    interface View {
        /**
         * Méthode déclenchée à l'ajout de la note <code>note</code>
         * @param note la note à ajouter
         */
        void onNoteAdded(Note note);

        /**
         * Méthode déclenchée lors du changement d'état du switch de verrouillage en ajout automatique
         * @param isChecked la valeur renvoyée par la position dudit switch
         */
        void onLockedCaptureToggled(boolean isChecked);

        /**
         * Affiche la dernière note ajoutée, qui est la note <code>note</code>
         * @param note la dernière note ajoutée
         */
        void displayLastAddedNote(Note note);
    }

    /**
     * L'interface faisant lieu de présenteur
     */
    interface Presenter {
        /**
         * Attache la vue <code>view</code> au présenteur
         * @param view
         */
        void attachView(View view);

        /**
         * Détache la vue courante du présenteur
         */
        void detachView();

        /**
         * Ajoute la note dont le contenu est <code>note_content</code> au repository
         * @param note_content le texte de la note à ajouter
         */
        void addNote(String note_content);

        /**
         * Met à jour le tag <code>tag</code> dans le repository si un tag correspondant y existe
         * @param tag le tag à mettre à jour
         */
        void updateTag(Tag tag);

        /**
         * Ajoute le tag <code>tag</code> au repository
         * @param tag le tag à ajouter
         */
        void addTag(Tag tag);

        /**
         * Récupère le tag dont le nom est <code>tagName</code> afin d'y associer la note <code>note</code> dans le repository
         * @param tagName le nom du tag
         * @param note la note à associée
         */
        void getTag(String tagName, Note note);

        /**
         * Récupère la dernière note ajoutée
         */
        void getLastAddedNote();

    }
}
