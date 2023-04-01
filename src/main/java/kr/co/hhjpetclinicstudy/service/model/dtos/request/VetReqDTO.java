package kr.co.hhjpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class VetReqDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class CREATE {

        @NotBlank(message = "Need a First Name")
        private String firstName;

        @NotBlank(message = "Need a Last Name")
        private String lastName;

        @NotEmpty(message = "Need a specialty")
        private List<Long> specialtiesId;
    }
}
