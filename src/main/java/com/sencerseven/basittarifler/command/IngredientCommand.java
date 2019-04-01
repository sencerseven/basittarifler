package com.sencerseven.basittarifler.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

    private Long id;

    private String description;

    List<IngredientDetailsCommand> ingredientDetailsCommands = new ArrayList<>();


}
