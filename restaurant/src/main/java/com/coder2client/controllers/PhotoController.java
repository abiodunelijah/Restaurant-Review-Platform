package com.coder2client.controllers;


import com.coder2client.dtos.PhotoDto;
import com.coder2client.entities.Photo;
import com.coder2client.mappers.PhotoMapper;
import com.coder2client.services.PhotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/photos")
public class PhotoController {

    private final PhotoService photoService;
    private final PhotoMapper photoMapper;

    @PostMapping
    public ResponseEntity<PhotoDto> uploadPhoto(@RequestParam("file") MultipartFile file) {
        Photo savedPhoto = photoService.uploadPhoto(file);
        return ResponseEntity.ok(photoMapper.toDto(savedPhoto));
    }
}
