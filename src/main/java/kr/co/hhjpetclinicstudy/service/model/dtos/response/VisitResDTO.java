package kr.co.hhjpetclinicstudy.service.model.dtos.response;

import kr.co.hhjpetclinicstudy.service.model.enums.PetType;
import lombok.*;

import java.time.LocalDate;

public class VisitResDTO {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class READ {

        private LocalDate visitDate;

        private String petName;

        private PetType petType;

        private String ownerFirstName;

        private String ownerLastName;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class READ_DETAIL {

        private LocalDate visitDate;

        private String description;

        private String petName;

        private PetType petType;

        private LocalDate petBirthDate;

        private String ownerFirstName;

        private String ownerLastName;

        private String ownerTelephone;
    }
}
