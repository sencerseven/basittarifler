package com.sencerseven.basittarifler.functions;

import org.springframework.web.multipart.MultipartFile;

public interface BasitTariflerHelpers {

    public String toSlug(String input);

    public MultipartFile addLogo(MultipartFile file);
}
