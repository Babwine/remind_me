package pfe.remindme.data.repository.notedisplay.mapper;

import pfe.remindme.data.Tag;
import pfe.remindme.data.entity.TagEntity;

/**
 * La classe pour le mapper qui transforme une Note en NoteEntity
 */
public class TagToTagEntityMapper {
    /**
     * Transforme le Tag <code>tag</code> en TagEntity
     * @param tag le Tag donné
     * @return la TagEntity obtenue à partir du Tag donné
     */
    public TagEntity map(Tag tag) {
        TagEntity tagEntity = new TagEntity();
        tagEntity.setTagName(tag.getTagName());

        tagEntity.setLinkedNotesIdList(tag.getLinkedNotes());

        return tagEntity;
    }
}
