package com.spring.springbootapp.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "images")
public class Image
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String dataImage;
    private String extension;
    private String slug;

    public Image(String name, String dataImage) {
        this.name = name;
        this.dataImage = dataImage;

    }

    public Image() {
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataImage() {
        return dataImage;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setExtensionFromBase64()
    {
        String[] cleanDataImage = (this.getDataImage()).split(",");
        String extension = cleanDataImage[0].substring(cleanDataImage[0].indexOf('/')+1, cleanDataImage[0].indexOf(';'));
        this.setExtension(extension);
    }
}
