package com.mlt.api.common.handler.error.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class IdsNotMatchException extends RuntimeException {

    private final List<String> values;

    private IdsNotMatchException(List<String> values) {
        this.values = values;
    }

    public String getErrorMessage() {
        return "Ids not match for values: " + values;
    }

    public static Builder builder(List<String> values) {
        return new Builder(values);
    }

    public static class Builder {
        private final List<String> values;

        public Builder(List<String> values) {
            this.values = values;
        }

        public IdsNotMatchException build() {
            return new IdsNotMatchException(values);
        }
    }

}
