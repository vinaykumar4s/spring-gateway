package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileProcessController {
    public static final String FILE_PART_HEADER = "file-part-id";

    @PostMapping("/destination")
    public ResponseEntity<String> processFilePart(@RequestHeader(FILE_PART_HEADER) String filePartId, @RequestBody byte[] filePartData) {
        return new ResponseEntity<String>("File part id:" + filePartId + "___________FileData:" + filePartData, HttpStatus.OK);
    }
}
