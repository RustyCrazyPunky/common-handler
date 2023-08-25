package com.mlt.api.common.handler.error.exception.validation;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryExistsException extends RuntimeException {

    private final String name;

    public CategoryExistsException(String name) {
        super("Category with name " + name + " already exists");
        this.name = name;
    }

}
