package com.spring.springbootapp.storage;

import com.spring.springbootapp.entity.Image;
import com.spring.springbootapp.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService{


    @Autowired
    ImageRepository imageRepository;
    Path path = Paths.get("images");
    @Override
    public void init() {
        Path path = Paths.get("images");
        try {
            Files.createDirectories(path);
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public Image store(Image file) throws IOException
    {
        init();
        String[] cleanDataImage = (file.getDataImage()).split(",");
        String extension = cleanDataImage[0].substring(cleanDataImage[0].indexOf('/')+1, cleanDataImage[0].indexOf(';'));
        file.setExtension(extension);
        byte[] decodedBytes = Base64.getDecoder().decode(cleanDataImage[1]);
        InputStream is = new ByteArrayInputStream(decodedBytes);
        BufferedImage newBi = ImageIO.read(is);
        file.setPath(path+"/"+file.getName()+"."+file.getExtension());

        ImageIO.write(newBi, file.getExtension(), new File(file.getPath()));
        file.setSlug(file.getName()+"_"+ UUID.randomUUID());
        imageRepository.saveAndFlush(file);

        return file;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {

        return null;
    }

    @Override
    public Optional<Image> findBySlug(String slug) {
        Image image = new Image();
        image.setSlug(slug);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.ENDING);

        Example<Image> example = Example.of(image, matcher);

        Optional<Image> img = imageRepository.findBy(example, q -> q.first());
        return img;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
