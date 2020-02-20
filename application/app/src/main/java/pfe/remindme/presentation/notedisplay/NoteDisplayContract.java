package pfe.remindme.presentation.notedisplay;

import java.util.List;
import pfe.remindme.presentation.notedisplay.adapter.NoteItemViewModel;

/**
 * L'interface faisant lieu de contrat entre la vue d'affichage des notes et son présenteur associé
 */
public interface NoteDisplayContract {
    /**
     * L'interface faisant lieu de vue
     */
    interface View {
        /**
         * Affiche la liste des notes courantes sur la vue
         */
        void displayNotes();

        /**
         * Initialise la liste des notes à afficher via la liste de ViewModel de notes <code>notes</code>
         * @param notes la liste de ViewModel de notes
         */
        void setNotes(List<NoteItemViewModel> notes);

        /**
         * Initialise la liste des notes à afficher via la liste d'ID de notes <code>noteIdList</code>
         * @param noteIdList la liste de ViewModel de notes
         */
        void setNotesByIdList(List<Integer> noteIdList);
    }

    /**
     * L'interface faisant lieu de présenteur
     */
    interface Presenter {
        /**
         * Attache la vue <code>view</code> au présenteur
         * @param view la vue à attacher
         */
        void attachView(View view);

        /**
         * Détache la vue courante du présenteur
         */
        void detachView();

        /**
         * Affiche toutes les notes du repository sur la vue
         */
        void displayAllNotes();

        /**
         * Supprime la note d'ID <code>noteId</code> du repository
         * @param noteId l'ID de la note à supprimer
         */
        void removeNote(int noteId);

        /**
         * Affiche les notes dont les tags associés contiennent la chaîne de caractères <code>str</code>
         * @param str la chaîne de caractères recherchée
         */
        void displayNotesFromStringLikeTag(String str);

        /**
         * Affiche la liste des notes dont les ID sont dans la liste <code>noteIdList</code>
         * @param noteIdList
         */
        void displayNotesFromIdList(List<Integer> noteIdList);

        /**
         * Supprime toutes les notes du repository
         * <bold>Cette fonctionnalité n'est pas utilisable par l'application pour le moment</bold>
         */
        void deleteAllNotes();

        /**
         * Supprime tous les tags du repository
         * <bold>Cette fonctionnalité n'est pas utilisable par l'application pour le moment</bold>
         */
        void deleteAllTags();
    }
}
