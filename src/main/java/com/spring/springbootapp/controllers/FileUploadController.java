package com.spring.springbootapp.controllers;

import com.spring.springbootapp.entity.Image;
import com.spring.springbootapp.repository.ImageRepository;
import com.spring.springbootapp.storage.StorageService;
import com.spring.springbootapp.storage.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("api/image")
public class FileUploadController
{
    @Autowired
    StorageServiceImpl storageService;
    @Autowired
    ImageRepository imageRepository;
    @PostMapping
    @ResponseBody
    public ResponseEntity<Image> handleFileUpload(@RequestBody Image image) throws IOException {
        Image img = storageService.store(image);
        return new ResponseEntity<>(img, HttpStatus.OK);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<byte[]> getOneImageSlug(@PathVariable("slug") String slug)
    {
        Optional<Image> img = storageService.findBySlug(slug);
        if(img.isPresent())
        {
            String[] cleanDataImage = (img.get().getDataImage()).split(",");
            byte[] decodedBytes = Base64.getDecoder().decode(cleanDataImage[1]);
            return new ResponseEntity<>(decodedBytes, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
