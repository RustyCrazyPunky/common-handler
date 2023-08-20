package com.mlt.api.common.handler.error.exception.notfound;

import com.mlt.api.common.handler.error.enums.NotFoundCodeEnum;

public class CategoryNotFoundException extends MltNotFoundException {

    public CategoryNotFoundException(String message, String code) {
        super(message, code, NotFoundCodeEnum.CATEGORY);
    }

    public static Builder builder(String productFilter) {
        return new Builder(productFilter);
    }

    public static class Builder {
        private final String productFilter;

        public Builder(String productFilter) {
            this.productFilter = productFilter;
        }

        public CategoryNotFoundException build() {
            String message = "Category not found for: " + this.productFilter;
            String code = NotFoundCodeEnum.getErrorCode(NotFoundCodeEnum.CATEGORY);
            return new CategoryNotFoundException(message, code);
        }
    }
}
