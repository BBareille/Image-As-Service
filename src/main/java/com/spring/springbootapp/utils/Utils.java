package com.spring.springbootapp.utils;

import com.spring.springbootapp.entity.Image;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.Base64;
import java.util.UUID;

public class Utils {

    public static Example<Image> getSlugMatcher(Image image)
    {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.ENDING);

        Example<Image> example = Example.of(image, matcher);
        return example;
    }

    public static byte[] getByteFromImage(Image image)
    {
        String[] cleanDataImage = (image.getDataImage()).split(",");
        return Base64.getDecoder().decode(cleanDataImage[1]);
    }
}
