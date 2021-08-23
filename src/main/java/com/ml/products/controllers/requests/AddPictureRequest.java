package com.ml.products.controllers.requests;

import com.ml.products.entities.Picture;
import com.ml.products.entities.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class AddPictureRequest {

    @Size(min = 1)
    @NotEmpty
    @Valid
    private List<NewPictureRequest> pictures;

    public AddPictureRequest(@Size(min = 1)
                             @NotEmpty
                             @Valid final List<NewPictureRequest> pictures) {
        this.pictures = pictures;
    }

    public Set<Picture> toModel(Product product) {
        return this.pictures.stream()
                .map(picture -> new Picture(picture.getType(), picture.getBase64(), product))
                .collect(Collectors.toSet());
    }

}
