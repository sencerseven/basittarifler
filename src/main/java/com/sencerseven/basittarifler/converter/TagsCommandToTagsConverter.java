package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.TagsCommand;
import com.sencerseven.basittarifler.domain.Tags;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TagsCommandToTagsConverter implements Converter<TagsCommand,Tags> {

    @Override
    public Tags convert(TagsCommand tagsCommand) {
       if(tagsCommand == null)
           return null;

       Tags tags = new Tags();
       tags.setId(tagsCommand.getId());
       tags.setTagsName(tagsCommand.getTagsName());

       return tags;

    }
}
