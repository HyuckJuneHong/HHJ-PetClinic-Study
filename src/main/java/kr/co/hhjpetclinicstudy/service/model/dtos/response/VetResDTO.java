package kr.co.hhjpetclinicstudy.service.model.dtos.response;

import lombok.*;

import java.util.List;

public class VetResDTO {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class READ {

        private String firstName;

        private String lastName;

        private List<String> specialtiesName;
    }
}
