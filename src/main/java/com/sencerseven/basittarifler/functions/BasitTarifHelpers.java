package com.sencerseven.basittarifler.functions;


import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Pattern;

import static org.thymeleaf.util.StringUtils.escapeJava;


public class BasitTarifHelpers {

        public static String toSlug(String input) {
            if(input != null){
            String norm = Normalizer.normalize(input, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(norm).replaceAll("").replace(" ", "-").toLowerCase();

            }
            return null;
        }


}
