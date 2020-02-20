package pfe.remindme.data.repository.notedisplay.mapper;

import java.util.ArrayList;
import java.util.List;

import pfe.remindme.data.Tag;
import pfe.remindme.data.entity.TagEntity;

/**
 * La classe pour le mapper qui transforme une TagEntity en Tag
 */
public class TagEntityToTagMapper {
    /**
     * Transforme la TagEntity <code>tagEntity</code> en Tag
     * @param tagEntity la TagEntity donnée
     * @return le Tag obtenu à partir de la TagEntity donnée
     */
    public Tag map(TagEntity tagEntity) {
        return new Tag(tagEntity.getTagName(), tagEntity.getLinkedNotesIdList());
    }

    /**
     * Transforme la liste de TagEntities <code>tagEntityList</code> en liste de Tags
     * @param tagEntityList la liste de TagEntities donnée
     * @return la liste de Tags obtenue à partir de la liste de TagEntities donnée
     */
    public List<Tag> map(List<TagEntity> tagEntityList) {
        List<Tag> tagList = new ArrayList<>();
        for (TagEntity tagEntity : tagEntityList) {
            tagList.add(this.map(tagEntity));
        }
        return tagList;
    }
}
