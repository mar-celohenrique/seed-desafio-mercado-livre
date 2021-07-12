package com.ml.commons.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ObjectError {

    private String message;

    private String field;

    private Object entry;

    @Deprecated
    public ObjectError() {}

}

