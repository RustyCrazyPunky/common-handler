package com.mlt.api.common.handler.error.exception;

import com.mlt.api.common.handler.error.enums.NotFoundCodeEnum;

public class ProductNotFoundException extends MltNotFoundException {

    public ProductNotFoundException(String message, String code) {
        super(message, code, NotFoundCodeEnum.PRODUCT);
    }

    public static Builder builder(String productFilter) {
        return new Builder(productFilter);
    }

    public static class Builder {
        private final String productFilter;

        public Builder(String productFilter) {
            this.productFilter = productFilter;
        }

        public ProductNotFoundException build() {
            String message = "Product not found for: " + this.productFilter;
            String code = NotFoundCodeEnum.getErrorCode(NotFoundCodeEnum.PRODUCT);
            return new ProductNotFoundException(message, code);
        }
    }
}
