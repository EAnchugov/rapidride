package com.aston.rapidride.http.minio;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;

@RestController
@AllArgsConstructor
public class MinioController {

    private MinioService minioService;

    @GetMapping("/files/{objectName}")
    public ResponseEntity<byte[]> getFile(@PathVariable String objectName) {
        System.out.println("Getting file " + objectName);
        try {
            InputStream inputStream = minioService.getFile(objectName);
            byte[] content = inputStream.readAllBytes();
            inputStream.close();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + objectName + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(content);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/files")
    public ResponseEntity<String> uploadFile(@RequestParam String objectName,
                                             @RequestParam String filePath) {
        minioService.addFile(objectName, filePath);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/files/names/{objectName}")
    public String getFileUrl(@PathVariable String objectName) {
        try {
            return minioService.getPresignedObjectUrl(objectName);
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }
    }
}
