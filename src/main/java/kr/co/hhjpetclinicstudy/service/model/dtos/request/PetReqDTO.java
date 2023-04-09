package kr.co.hhjpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

public class PetReqDTO {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CREATE{

        @NotBlank(message = "Need a Pet Name")
        private String petName;

        private LocalDate birthDate;

        @NotBlank(message = "Need a Pet Type")
        private String petType;

        @NotNull(message = "Need a Owner")
        private Long ownerId;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UPDATE {

        @NotNull(message = "Need a pet")
        private Long petId;

        private String petName;

        private LocalDate birthDate;

        private String petType;
    }
}
