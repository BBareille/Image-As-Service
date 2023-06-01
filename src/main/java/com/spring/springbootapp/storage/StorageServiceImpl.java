package com.spring.springbootapp.storage;

import com.spring.springbootapp.entity.Image;
import com.spring.springbootapp.repository.ImageRepository;
import com.spring.springbootapp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService{


    @Autowired
    ImageRepository imageRepository;
    @Override
    public Image store(Image file) throws IOException
    {
        file.setExtensionFromBase64();
        imageRepository.saveAndFlush(file);

        return file;
    }
    @Override
    public ArrayList<Image> storeAll(ArrayList<Image> imagesList) {
        for (Image image: imagesList)
        {
            image.setExtensionFromBase64();
        }
        imageRepository.saveAllAndFlush(imagesList);
        return null;
    }

    @Override
    public Optional<Image> findBySlug(String slug) {
        Image image = new Image();
        image.setSlug(slug);
        Optional<Image> img = imageRepository.findBy(Utils.getSlugMatcher(image), q -> q.first());
        return img;
    }
}
