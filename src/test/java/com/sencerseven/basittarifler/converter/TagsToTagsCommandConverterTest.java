package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.TagsCommand;
import com.sencerseven.basittarifler.domain.Tags;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class TagsToTagsCommandConverterTest {

    TagsToTagsCommandConverter tagsToTagsCommandConverter;

    private static final Long ID = 1L;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        tagsToTagsCommandConverter = new TagsToTagsCommandConverter();
    }

    @Test
    public void nullTest(){
        assertNull(tagsToTagsCommandConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(tagsToTagsCommandConverter.convert(new Tags()));
    }

    @Test
    public void convert() {
        Tags tags = new Tags();
        tags.setId(ID);

        TagsCommand tagsCommand = tagsToTagsCommandConverter.convert(tags);

        assertEquals(ID,tagsCommand.getId());
    }
}