package com.sencerseven.basittarifler.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class JsonResponder {

    private String status;
    private Object object;

}
