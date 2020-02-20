package pfe.remindme.data;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    private String tagName;
    private List<Integer> linkedNotes;

    /**
     * Constructeur
     * @param tagName le nom du tag
     */
    public Tag(String tagName) {
        this.tagName = tagName;
        this.linkedNotes = new ArrayList<>();
    }

    /**
     * Contructeur incluant la liste des notes, utilisé lors de la récupération du tag
     * @param tagName le nom du tag
     * @param linkedNotes la liste des ID des notes associées au tag
     */
    public Tag(String tagName, List<Integer> linkedNotes) {
        this.tagName = tagName;
        this.linkedNotes = linkedNotes;
    }

    /**
     * Ajoute la note <code>n</code> à la liste des notes associées à ce tag
     * @param n
     */
    public void addNote(int n) {
        linkedNotes.add(n);
    }

    /**
     * Renvoie le nom du tag
     * @return le nom du tag
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Initialise le nom du tag avec la valeur <code>tagName</code> donnée
     * @param tagName le nom du tag
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Renvoie la liste des notes associées au tag
     * @return la liste des ID des notes associées au tag
     */
    public List<Integer> getLinkedNotes() {
        return linkedNotes;
    }

    /**
     * Initilalise la liste des notes associées au tags avec la liste <code>linkedNotes</code>
     * @param linkedNotes la liste des ID des notes donnée
     */
    public void setLinkedNotes(List<Integer> linkedNotes) {
        this.linkedNotes = linkedNotes;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Tag) {
            if (((Tag) other).getTagName().equals(this.getTagName())) {
                return true;
            } else return false;
        } else return false;
    }
}
