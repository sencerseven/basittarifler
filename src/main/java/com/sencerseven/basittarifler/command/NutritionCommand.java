package com.sencerseven.basittarifler.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NutritionCommand {

    private Long id;

    private String protine;

    private String fiber;

    private String fat;

    private String energy;

    private String saturatedFat;

    private String carbonhydrate;

    private String cholesterol;

    private String sugar;

}
