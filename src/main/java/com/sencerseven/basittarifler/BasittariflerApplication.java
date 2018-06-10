package com.sencerseven.basittarifler;

import com.sencerseven.basittarifler.service.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class BasittariflerApplication implements CommandLineRunner {


    @Value("${jsa.s3.uploadfile}")
    private String uploadFilePath;

    @Value("${jsa.s3.key}")
    private String downloadKey;

    public static void main(String[] args) {
        SpringApplication.run(BasittariflerApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        //System.out.println("---------------- START UPLOAD FILE ----------------");
        //s3Services.uploadFile(downloadKey, uploadFilePath);
    }
}
