package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
public class FileUploadController {

    public static final String FILE_PART_HEADER = "file-part-id";
    Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    private static final String URI_DESTINATION = "http://localhost/destination";
    private final RestTemplate restTemplate;

    @Autowired
    private FileUploadController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/upload")
    public String uploadFile(@RequestParam(value = FILE_PART_HEADER, defaultValue = "12334343") String filePartData) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(FILE_PART_HEADER, filePartData);
        HttpEntity<byte[]> requestEntity = new HttpEntity<>(String.valueOf(UUID.randomUUID()).getBytes(), headers);
        ResponseEntity<String> response = restTemplate.exchange(URI_DESTINATION, HttpMethod.POST, requestEntity, String.class);
        String responseBody = response.getBody();
        logger.info("Response:" + responseBody);
        return responseBody + "___________Status code:" + response.getStatusCode().value();

    }
}
