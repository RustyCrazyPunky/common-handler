package com.mlt.api.common.handler.error.handlers;

import com.mlt.api.common.domain.MltMessage;
import com.mlt.api.common.domain.response.MltData;
import com.mlt.api.common.domain.response.MltResponse;
import com.mlt.api.common.handler.error.exception.IdsNotMatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public MltResponse<MltData> handleArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<MltMessage> errors = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            errors.add(MltMessage.builder()
                                 .code(fieldError.getField())
                                 .message(fieldError.getDefaultMessage())
                                 .build());
        });
        return MltResponse.builder().messages(errors).build();
    }

    @ExceptionHandler(IdsNotMatchException.class)
    public MltResponse<MltData> handleIdsNotMatchException(IdsNotMatchException exception) {
        return MltResponse.builder().error(MltMessage.builder().message(exception.getErrorMessage()).build()).build();
    }


}
