package com.sencerseven.basittarifler.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TagsCommand {

    private Long id;

    private String tagsName;

    private String selectTagList;

}
