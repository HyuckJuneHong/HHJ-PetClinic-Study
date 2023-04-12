package kr.co.hhjpetclinicstudy.infrastructure.error.exception;

import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;

/**
 * 이미 존재하는 리소스를 생성하려고 할 때 발생하는 예외
 */
public class DuplicatedException extends BusinessLogicException{

    public DuplicatedException(ResponseStatus responseStatus) {

        super(responseStatus);
    }

    public DuplicatedException(String message) {

        super(message);
    }
}
