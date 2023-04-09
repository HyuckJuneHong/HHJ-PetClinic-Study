package kr.co.hhjpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

public class VetReqDTO {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CREATE {

        @NotBlank(message = "Need a First Name")
        private String firstName;

        @NotBlank(message = "Need a Last Name")
        private String lastName;

        @NotEmpty(message = "Need a specialty")
        private List<String> specialtiesName;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UPDATE {

        @NotNull(message = "need a vet")
        private Long vetId;

        private String firstName;

        private String lastName;

        private List<String> specialtiesName;
    }
}
