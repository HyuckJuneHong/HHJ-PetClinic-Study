package kr.co.hhjpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OwnerReqDTO {

    @NoArgsConstructor
    @Getter
    @AllArgsConstructor
    @Builder
    public static class CREATE {

        @NotBlank(message = "need a first name")
        private String firstName;

        @NotBlank(message = "need a last name")
        private String lastName;

        @NotBlank(message = "need a address")
        private String address;

        @NotBlank(message = "need a city")
        private String city;

        @NotBlank(message = "need a telephone")
        private String telephone;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class UPDATE {

        @NotNull(message = "need an owner")
        private Long ownerId;

        private String firstName;

        private String lastName;

        private String address;

        private String city;

        private String telephone;
    }
}
