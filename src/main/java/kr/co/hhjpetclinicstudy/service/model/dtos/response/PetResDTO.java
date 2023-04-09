package kr.co.hhjpetclinicstudy.service.model.dtos.response;

import kr.co.hhjpetclinicstudy.service.model.enums.PetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class PetResDTO {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class READ {

        private String petName;

        private PetType petType;

        private LocalDate birthDate;

        private String ownerFirstName;

        private String ownerLastName;

        private String ownerTelephone;
    }
}
