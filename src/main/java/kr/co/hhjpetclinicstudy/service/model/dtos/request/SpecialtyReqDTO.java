package kr.co.hhjpetclinicstudy.service.model.dtos.request;

import lombok.*;

import java.util.Set;

public class SpecialtyReqDTO {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UPDATE {

        private Set<String> specialtiesName;
    }
}
