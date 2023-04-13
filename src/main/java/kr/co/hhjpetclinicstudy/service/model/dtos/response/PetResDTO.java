package kr.co.hhjpetclinicstudy.service.model.dtos.response;

import kr.co.hhjpetclinicstudy.service.model.enums.PetType;
import lombok.*;

import java.time.LocalDate;

public class PetResDTO {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class READ {

        private String petName;

        private PetType petType;

        private LocalDate birthDate;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class READ_DETAIL {

        private String petName;

        private PetType petType;

        private LocalDate birthDate;

        private String ownerFirstName;

        private String ownerLastName;

        private String ownerTelephone;
    }

}
