package kr.co.hhjpetclinicstudy.infrastructure.error.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResponseStatus {

    //Success Status
    SUCCESS_OK("요청이 성공적으로 처리되었습니다.", HttpStatus.OK),
    SUCCESS_CREATE("요청이 성공적으로 처리되어 새로운 리소스가 생성되었습니다.", HttpStatus.CREATED),
    SUCCESS_ACCEPTED("요청이 성공적으로 처리되었지만, 결과가 아직 완료되지 않았습니다.", HttpStatus.ACCEPTED),
    SUCCESS_NO_CONTENT("요청이 성공적으로 처리되었지만, 응답 데이터가 없습니다.", HttpStatus.NO_CONTENT),

    //fail Status
    FAIL_BAD_REQUEST("클라이언트의 요청이 잘못되었습니다.", HttpStatus.BAD_REQUEST),
    FAIL_UNAUTHORIZED("클라이언트가 인증되지 않았습니다.", HttpStatus.UNAUTHORIZED),
    FAIL_FORBIDDEN("클라이언트가 요청한 리소스에 접근할 권한이 없습니다.", HttpStatus.FORBIDDEN),
    FAIL_NOT_FOUND("클라이언트가 요청한 리소스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    FAIL_METHOD_NOT_ALLOWED("클라이언트가 요청한 HTTP 메소드가 허용되지 않았습니다.", HttpStatus.METHOD_NOT_ALLOWED),

    //custom : valid
    FAIL_INVALID_PARAMETER("파라미터 값이 유효하지 않습니다.", HttpStatus.BAD_REQUEST),

    //custom : owner
    FAIL_OWNER_NOT_FOUND("클라이언트가 요청한 소유자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    FAIL_TELEPHONE_DUPLICATED("클라이언트의 전화번호가 중복되었습니다.", HttpStatus.BAD_REQUEST),

    //custom : pet
    FAIL_PET_NOT_FOUND("클라이언트가 요청한 반려동물을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    FAIL_PET_TYPE_NOT_FOUND("클라이언트가 요청한 반려동울 종류는 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    //custom : vet, specialty, vetSpecialty
    FAIL_VET_NOT_FOUND("클라이언트가 요청한 수의사를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    FAIL_SPECIALTY_NOT_FOUND("클라이언트가 요청한 학위를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    FAIL_VET_SPECIALTY_NOT_FOUND("클라이언트가 요청한 연관된 수의사와 학위들을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    //custom : visit
    FAIL_VISIT_NOT_FOUND("클라이언트가 요청한 방문기록을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    //custom : admin
    FAIL_USER_ROLE_NOT_FOUND("클라이언트가 요청한 권한을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ;

    private String message;

    private HttpStatus statusCode;
}
