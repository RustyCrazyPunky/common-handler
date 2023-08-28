package com.mlt.api.common.handler.error.handlers;

import com.mlt.api.common.domain.MltMessage;
import com.mlt.api.common.domain.response.MltData;
import com.mlt.api.common.domain.response.MltResponse;
import com.mlt.api.common.handler.error.exception.validation.CategoryExistsException;
import com.mlt.api.common.handler.error.exception.validation.IdsNotFoundException;
import com.mlt.api.common.handler.error.exception.validation.IdsNotMatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class ValidationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MltResponse<MltData>> handleArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("Validation error: {}", exception.getBindingResult());
        BindingResult bindingResult = exception.getBindingResult();
        List<MltMessage> errors = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            errors.add(MltMessage.builder()
                                 .code("VAL-0001")
                                 .message(fieldError.getField() + ": " + fieldError.getDefaultMessage())
                                 .type("VALIDATION")
                                 .status(HttpStatus.BAD_REQUEST.value())
                                 .timestamp(LocalDateTime.now())
                                 .build());
        });
        return ResponseEntity.badRequest().body(MltResponse.builder().messages(errors).build());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<MltResponse<MltData>> handleArgumentNotValidException(MissingServletRequestParameterException exception) {
        log.error("Validation error: {}", exception.getDetailMessageArguments());
        List<MltMessage> errors = new ArrayList<>();
        for (Object detailMessageArgument : Objects.requireNonNull(exception.getDetailMessageArguments())) {
            errors.add(MltMessage.builder()
                                 .code("VAL-0002")
                                 .status(400)
                                 .message(detailMessageArgument.toString() + " is required")
                                 .type("VALIDATION")
                                 .status(HttpStatus.BAD_REQUEST.value())
                                 .timestamp(LocalDateTime.now())
                                 .build());
        }
        return ResponseEntity.badRequest().body(MltResponse.builder().messages(errors).build());
    }

    @ExceptionHandler(IdsNotMatchException.class)
    public ResponseEntity<MltResponse<MltData>> handleIdsNotMatchException(IdsNotMatchException exception) {
        log.error("Resource not found: ", exception);
        return ResponseEntity.badRequest()
                             .body(MltResponse.builder()
                                              .error(MltMessage.builder().message(exception.getErrorMessage()).build())
                                              .build());
    }

    @ExceptionHandler(IdsNotFoundException.class)
    public ResponseEntity<MltResponse<MltData>> handleIdsNotMatchException(IdsNotFoundException exception) {
        log.error("Resource not found: ", exception);
        return ResponseEntity.badRequest()
                             .body(MltResponse.builder()
                                              .error(MltMessage.builder().message(exception.getErrorMessage()).build())
                                              .build());
    }

    @ExceptionHandler(CategoryExistsException.class)
    public ResponseEntity<MltResponse<MltData>> handleCategoryExistsException(CategoryExistsException exception) {
        log.error("Category exists: ", exception);
        return ResponseEntity.badRequest()
                             .body(MltResponse.builder()
                                              .error(MltMessage.builder().message(exception.getMessage()).build())
                                              .build());


    }
}
