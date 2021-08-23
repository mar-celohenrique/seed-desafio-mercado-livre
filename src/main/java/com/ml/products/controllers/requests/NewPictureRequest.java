package com.ml.products.controllers.requests;

import com.ml.products.entities.PictureType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewPictureRequest {

    @NotEmpty
    private String base64;

    @NotNull
    private PictureType type;

    public NewPictureRequest(@NotEmpty final String base64,
                             @NotNull final PictureType type) {
        this.base64 = base64;
        this.type = type;
    }

}
