package com.mlt.api.common.handler.error.exception.validation;

import java.util.List;

public class IdsNotFoundException extends RuntimeException {

    private final List<String> values;
    private final String field;

    private IdsNotFoundException(String field, List<String> values) {
        super("Ids not found for values: " + field + " " + values);
        this.field = field;
        this.values = values;
    }

    public String getErrorMessage() {
        return "Ids not found for values: " + this.field + " " + values;
    }

    public static IdsNotFoundException.Builder builder(String field, List<String> values) {
        return new IdsNotFoundException.Builder(field, values);
    }

    public static class Builder {
        private final List<String> values;
        private final String field;

        public Builder(String field, List<String> values) {
            this.field = field;
            this.values = values;
        }

        public IdsNotFoundException build() {
            return new IdsNotFoundException(field, values);
        }
    }
}
