# Image-As-Service


===========================================================================

GET => localhost:8080/api/image/{slug} // This link can be used  for <img src="{url}" />

===========================================================================

POST => localhost:8080/api/image

Body Request =>
```
{
    "name": "photo", //name is unique !!
    "slug": "my-beautiful-photo", // If ignored it will be " name-{random-uuid} "
    "dataImage": " data:image/jpeg;base64,/9j/4AAQSkZJRgABAQIAHAAcAAD/ ....... " //dataImage should always start with " data:image/{ extension };base64, "
}

```

RETURN 
  200 Success if it worked


===========================================================================

POST => localhost:8080/api/image/all

Body Request =>
```
[
  {
      "name": "photo", 
      "slug": "my-beautiful-photo", 
      "dataImage": " data:image/jpeg;base64,/9j/4AAQSkZJRgABAQIAHAAcAAD/ ....... "
  },
  {
      "name": "photo2", 
      "slug": "my-beautiful-photo2", 
      "dataImage": " data:image/jpeg;base64,/9j/4AAQSkZJRgABAQIAHAAcAAD/ ....... "
  },
  {
      "name": "photo3", 
      "slug": "my-beautiful-photo3", 
      "dataImage": " data:image/jpeg;base64,/9j/4AAQSkZJRgABAQIAHAAcAAD/ ....... "
  },
  {
    ...
  }
]
```

RETURN 
  200 Success if it worked


===========================================================================
