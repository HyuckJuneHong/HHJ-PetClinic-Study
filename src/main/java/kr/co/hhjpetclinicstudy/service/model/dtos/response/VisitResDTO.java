package kr.co.hhjpetclinicstudy.service.model.dtos.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

        private String firstName;

        private String lastName;
    }
}
