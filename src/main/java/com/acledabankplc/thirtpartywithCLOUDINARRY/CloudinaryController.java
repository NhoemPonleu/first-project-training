package com.acledabankplc.thirtpartywithCLOUDINARRY;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cloudinary")
public class CloudinaryController {

    private final CloudinaryService cloudinaryService;

    @Autowired
    public CloudinaryController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            Map result = cloudinaryService.upload(file);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to upload image"));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map> deleteFile(@RequestParam("public_id") String publicId) {
        try {
            Map result = cloudinaryService.delete(publicId);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(Map.of("error", "Failed to delete image"));
        }
    }
}
