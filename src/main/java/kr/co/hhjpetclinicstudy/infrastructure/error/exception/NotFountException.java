package kr.co.hhjpetclinicstudy.infrastructure.error.exception;

import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;

/**
 * 요청한 리소스가 존재하지 않을 때 발생하는 예외
 */
public class NotFountException extends BusinessLogicException {

    public NotFountException(ResponseStatus responseStatus) {
        super(responseStatus);
    }

    public NotFountException(String message) {
        super(message);
    }
}
