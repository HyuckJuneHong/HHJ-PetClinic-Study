package kr.co.hhjpetclinicstudy.infrastructure.error.handler;

import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseErrorFormat;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ValidExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatusCode status,
                                                               WebRequest request) {

        log.warn("handleIllegalArgument", ex);

        final ResponseStatus responseStatus = ResponseStatus.FAIL_INVALID_PARAMETER;
        return handleExceptionInternal(ex, responseStatus);
    }

    private ResponseEntity<Object> handleExceptionInternal(final BindException e,
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
