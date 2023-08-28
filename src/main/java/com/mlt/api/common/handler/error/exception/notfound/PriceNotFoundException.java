package com.mlt.api.common.handler.error.exception.notfound;

import com.mlt.api.common.handler.error.enums.NotFoundCodeEnum;

public class PriceNotFoundException extends MltNotFoundException {

    public PriceNotFoundException(String message, String code) {
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

        public PriceNotFoundException build() {
            String message = "Price not found for: " + this.productFilter;
            String code = NotFoundCodeEnum.getErrorCode(NotFoundCodeEnum.PRICE);
            return new PriceNotFoundException(message, code);
        }
    }
}
