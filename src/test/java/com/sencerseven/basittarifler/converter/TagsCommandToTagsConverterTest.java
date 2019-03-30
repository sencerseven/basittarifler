package com.sencerseven.basittarifler.converter;

import com.sencerseven.basittarifler.command.TagsCommand;
import com.sencerseven.basittarifler.domain.Tags;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class TagsCommandToTagsConverterTest {

    TagsCommandToTagsConverter tagsCommandToTagsConverter;

    private static final Long ID = 1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        tagsCommandToTagsConverter = new TagsCommandToTagsConverter();
    }

    @Test
    public void nullTest() {
        assertNull(tagsCommandToTagsConverter.convert(null));
    }

    @Test
    public void emptyTest() {
        assertNotNull(tagsCommandToTagsConverter.convert(new TagsCommand()));
    }

    @Test
    public void convert() {
        TagsCommand tagsCommand = new TagsCommand();
        tagsCommand.setId(ID);
        Tags tags = tagsCommandToTagsConverter.convert(tagsCommand);

        assertEquals(ID,tags.getId());
    }
}