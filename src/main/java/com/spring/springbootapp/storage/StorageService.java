package com.spring.springbootapp.storage;

import com.spring.springbootapp.entity.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


public interface StorageService
{
    Image store(Image file) throws IOException;

    Optional<Image> findBySlug(String slug);

    ArrayList<Image> storeAll(ArrayList<Image> imagesList);
}
