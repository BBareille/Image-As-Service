package com.spring.springbootapp.controllers;

import com.spring.springbootapp.entity.Image;
import com.spring.springbootapp.repository.ImageRepository;
import com.spring.springbootapp.storage.StorageService;
import com.spring.springbootapp.storage.StorageServiceImpl;
import com.spring.springbootapp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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
        if(image.getSlug() == null)
            img.setSlug(img.getName() + "-"+ UUID.randomUUID());
        return new ResponseEntity<>(img, HttpStatus.OK);
    }

    @PostMapping("/all")
    @ResponseBody
    public ResponseEntity<ArrayList<Image>> handleMultipleFileUpload(@RequestBody ArrayList<Image> imageArrayList)
    {
        System.out.println(imageArrayList);
        ArrayList<Image> images = storageService.storeAll(imageArrayList);
        return new ResponseEntity<>(images,HttpStatus.OK);
    }

    @GetMapping(value = "/{slug}", produces = "image/jpeg")
    @ResponseBody
    public ResponseEntity<byte[]> getOneImageSlug(@PathVariable("slug") String slug)
    {
        Optional<Image> img = storageService.findBySlug(slug);
        if(img.isPresent())
        {
            Image image = img.get();
            byte[] decodedBytes = Utils.getByteFromImage(image);
            return new ResponseEntity<>(decodedBytes, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
