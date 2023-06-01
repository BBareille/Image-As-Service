package com.spring.springbootapp.controllers;

import com.spring.springbootapp.entity.Image;
import com.spring.springbootapp.repository.ImageRepository;
import com.spring.springbootapp.storage.StorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;
import java.util.Optional;

@Controller
@RequestMapping(value = "/web/image", produces = "image/jpeg")
public class WebImageController
{

    @Autowired
    StorageServiceImpl storageService;
    @Autowired
    ImageRepository imageRepository;

    @GetMapping()
    public ResponseEntity<byte[]> getOneImage(@RequestParam(name = "id") Integer id)
    {
        Image img = imageRepository.findById(id).get();
        if(img == null)
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        String[] cleanDataImage = (img.getDataImage()).split(",");
        byte[] decodedBytes = Base64.getDecoder().decode(cleanDataImage[1]);
        return new ResponseEntity<>(decodedBytes, HttpStatus.OK);
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
