package kr.co.hhjpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;
import java.util.Set;

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
        private Set<String> specialtiesName;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ADD_DELETE {

        private Set<String> specialtiesName;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CONDITION {

        private List<Long> vetIds;
    }
}
