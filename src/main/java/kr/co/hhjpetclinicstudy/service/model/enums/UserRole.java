package kr.co.hhjpetclinicstudy.service.model.enums;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum UserRole {

    USER_ROLE("일반"),

    ADMIN_ROLE("관리자");

    String userRole;

    public static UserRole of(String userRole) {

        return Arrays.stream(UserRole.values())
                .filter(role -> role.toString().equalsIgnoreCase(userRole))
                .findAny().orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_USER_ROLE_NOT_FOUND));
    }
}
