package com.sencerseven.basittarifler.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeTipsCommand {

    private Long id;

    private String description;


    private int viewRows;
}
