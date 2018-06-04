package com.sencerseven.basittarifler.command;

import com.sencerseven.basittarifler.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {

    private Long id;

    private String categoryName;

    private String categoryDescription;

}
