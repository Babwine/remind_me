package pfe.remindme.data.repository.notedisplay.mapper;

import java.util.ArrayList;
import java.util.List;

import pfe.remindme.data.Note;
import pfe.remindme.data.Tag;
import pfe.remindme.data.entity.TagEntity;
import pfe.remindme.data.repository.notedisplay.NoteDisplayRepository;

public class TagEntityToTagMapper {
    public Tag map(TagEntity tagEntity) {
        return new Tag(tagEntity.getTagName(), tagEntity.getLinkedNotesIdList());
    }

    public List<Tag> map(List<TagEntity> tagEntityList) {
        List<Tag> tagList = new ArrayList<>();
        for (TagEntity tagEntity : tagEntityList) {
            tagList.add(this.map(tagEntity));
        }
        return tagList;
    }
}
