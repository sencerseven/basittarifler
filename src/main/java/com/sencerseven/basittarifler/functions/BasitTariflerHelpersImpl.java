package com.sencerseven.basittarifler.functions;


import com.sencerseven.basittarifler.model.MultipartImage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Pattern;

@Service
public class BasitTariflerHelpersImpl implements BasitTariflerHelpers {

    @Override
    public String toSlug(String input) {
        if (input != null) {
            String norm = Normalizer.normalize(input, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(norm).replaceAll("").replace(" ", "-").toLowerCase();

        }
        return null;
    }

    @Override
    public MultipartFile addLogo(MultipartFile file) {
        BufferedImage image = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            image = ImageIO.read(file.getInputStream());

            Graphics g = image.getGraphics();

            BufferedImage img1 = ImageIO.read(new ClassPathResource("static/images/logo.png").getInputStream());

            /*g.setFont(g.getFont().deriveFont(30f));
            g.setColor(Color.green);
            g.drawString("Basit Yemek Tarifleri", 100, 100);
            */
            g.drawImage(img1,0,0,null);
            g.dispose();


            String typeName = "jpg";
            if (file.getContentType().equals(MediaType.IMAGE_PNG))
                typeName = "png";

            ImageIO.write(image,typeName,baos);

        } catch (IOException e) {
            e.printStackTrace();
        }
        MultipartFile multipartFile = new MultipartImage(baos.toByteArray(),file.getOriginalFilename(),
                file.getOriginalFilename(),
                file.getContentType(),false,
                baos.size());

        return multipartFile;

    }


}
