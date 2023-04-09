package kr.co.hhjpetclinicstudy.infrastructure.error.exception;

import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;

/**
 * 인증되지 않은 사용자가 보호된 리소스에 액세스하려고 할 때 발생하는 예외
 */
public class UnauthorizedException extends BusinessLogicException{

    public UnauthorizedException(ResponseStatus responseStatus) {
        super(responseStatus);
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
