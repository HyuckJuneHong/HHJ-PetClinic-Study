package kr.co.hhjpetclinicstudy.service.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OwnerResDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class READ {

        private String firstName;

        private String lastName;

        private String address;

        private String city;

        private String telephone;
    }
}
