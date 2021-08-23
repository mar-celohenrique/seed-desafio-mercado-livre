package com.ml.commons.storage;

import com.ml.products.entities.Picture;

import java.util.Set;

public interface StorageClient {

    Set<Picture> store(Set<Picture> pictures);

}
