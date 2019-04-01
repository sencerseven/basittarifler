package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.TagsCommand;
import com.sencerseven.basittarifler.domain.Tags;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TagsToTagsCommandConverter implements Converter<Tags,TagsCommand> {

    @Override
    public TagsCommand convert(Tags tags) {
        if(tags == null)
            return null;

        TagsCommand tagsCommand = new TagsCommand();
        tagsCommand.setId(tags.getId());
        tagsCommand.setTagsName(tags.getTagsName());

        return tagsCommand;
    }
}
