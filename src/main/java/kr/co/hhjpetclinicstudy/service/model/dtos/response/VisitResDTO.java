package kr.co.hhjpetclinicstudy.service.model.dtos.response;

import kr.co.hhjpetclinicstudy.service.model.enums.PetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class VisitResDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class READ {

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
