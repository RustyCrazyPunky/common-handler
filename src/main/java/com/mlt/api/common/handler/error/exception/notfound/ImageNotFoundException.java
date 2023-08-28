package com.mlt.api.common.handler.error.exception.notfound;

import com.mlt.api.common.handler.error.enums.NotFoundCodeEnum;

public class ImageNotFoundException extends MltNotFoundException {

    public ImageNotFoundException(String message, String code) {
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

        public ImageNotFoundException build() {
            String message = "Image not found for: " + this.productFilter;
            String code = NotFoundCodeEnum.getErrorCode(NotFoundCodeEnum.IMAGE);
            return new ImageNotFoundException(message, code);
        }
    }
}
