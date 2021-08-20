package com.ml.products.controllers.requests;

import com.ml.products.entities.PictureType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewPictureRequest {

    @Getter
    @NotEmpty
    private String base64;

    @Getter
    @NotNull
    private PictureType type;

}
