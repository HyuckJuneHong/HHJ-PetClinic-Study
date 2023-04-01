package kr.co.hhjpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class PetReqDTO {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class CREATE{
        @NotBlank(message = "Need a Pet Name")
        private String name;

        private LocalDate birthDate;

        @NotBlank(message = "Need a Pet Type")
        private String petType;

        @NotNull(message = "Need a Owner")
        private Long ownerId;
    }
}