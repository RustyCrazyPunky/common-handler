package com.mlt.api.common.handler.error.handlers;

import com.mlt.api.common.domain.MltMessage;
import com.mlt.api.common.domain.response.MltData;
import com.mlt.api.common.domain.response.MltResponse;
import com.mlt.api.common.handler.error.exception.MltNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class NotFoundHandler {

    @ExceptionHandler(MltNotFoundException.class)
    public ResponseEntity<MltResponse<MltData>> handleMltNotFoundException(MltNotFoundException ex) {
        log.error("Not found error: ", ex);
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(MltResponse.builder()
                                                             .error(MltMessage.builder()
                                                                              .message(ex.getMessage())
                                                                              .code(ex.getCode())
                                                                              .type(ex.getType().name())
                                                                              .timestamp(LocalDateTime.now())
                                                                              .status(status.value())
                                                                              .build())
                                                             .build());
    }


}
