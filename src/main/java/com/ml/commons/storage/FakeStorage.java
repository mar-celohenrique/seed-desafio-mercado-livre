package com.ml.commons.storage;

import com.ml.products.entities.Picture;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Profile({"default", "dev"})
@Component
public class FakeStorage implements StorageClient {

    @Override
    public Set<Picture> store(Set<Picture> pictures) {
        return pictures.stream().peek(picture -> {
            picture.setBase64(null);
            picture.setLink("https://source.unsplash.com/random/".concat(picture.getName()));
        }).collect(Collectors.toSet());
    }

}
