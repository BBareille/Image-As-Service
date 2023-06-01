package com.spring.springbootapp.storage;

import com.spring.springbootapp.entity.Image;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;


public interface StorageService
{
    void init();

    Image store(Image file) throws IOException;

    Stream<Path> loadAll();

    Path load(String filename);

    <T> Optional<T> findBySlug(String slug);

    Resource loadAsResource(String filename);

    void deleteAll();
}
