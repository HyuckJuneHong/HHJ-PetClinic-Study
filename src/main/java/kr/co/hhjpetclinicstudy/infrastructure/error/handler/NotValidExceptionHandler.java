package kr.co.hhjpetclinicstudy.infrastructure.error.handler;

import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseErrorFormat;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class NotValidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErrorFormat> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        log.warn("======= HandleMethodArgumentNotValidException Start =======", e);
        log.info("======= HandleMethodArgumentNotValidException End =======");

        final ResponseStatus responseStatus = ResponseStatus.FAIL_INVALID_PARAMETER;

        return handleExceptionInternal(e, responseStatus);
    }

    private ResponseEntity<ResponseErrorFormat> handleExceptionInternal(final BindException e,
                                                                        final ResponseStatus responseStatus) {

        return ResponseEntity
                .status(responseStatus.getStatusCode())
                .body(makeResponseErrorFormat(e, responseStatus));
    }

    private ResponseErrorFormat makeResponseErrorFormat(final BindException e,
                                                        final ResponseStatus responseStatus) {

        final List<ResponseErrorFormat.ValidationException> validationExceptions = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ResponseErrorFormat.ValidationException::of)
                .collect(Collectors.toList());

        return ResponseErrorFormat.builder()
                .message(responseStatus.getMessage())
                .statusCode(responseStatus.getStatusCode())
                .validationExceptions(validationExceptions)
                .build();
    }
}
