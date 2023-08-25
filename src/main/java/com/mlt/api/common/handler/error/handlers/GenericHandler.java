package com.mlt.api.common.handler.error.handlers;

import com.mlt.api.common.domain.MltMessage;
import com.mlt.api.common.domain.response.MltData;
import com.mlt.api.common.domain.response.MltResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GenericHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<MltResponse<MltData>> handleException(Exception ex) {
        log.error("Handling generic error:", ex);
        return ResponseEntity.internalServerError()
                             .body(MltResponse.builder()
                                              .error(MltMessage.builder()
                                                               .message(ex.getMessage())
                                                               .status(500)
                                                               .timestamp(
                                                                       LocalDateTime.now())
                                                               .build())
                                              .build());
    }
}
