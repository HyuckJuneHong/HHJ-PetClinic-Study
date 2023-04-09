package kr.co.hhjpetclinicstudy.service.model.dtos.response;

import lombok.*;

public class OwnerResDTO {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class READ {

        private String firstName;

        private String lastName;

        private String address;

        private String city;

        private String telephone;
    }
}
