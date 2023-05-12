package kr.co.hhjpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class VisitReqDTO {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CREATE {

        @NotNull(message = "Need a Visit date")
        private LocalDate visitDate;

        @NotBlank(message = "Need a Description")
        private String description;

        @NotNull(message = "need a pet")
        private Long petId;

        @NotNull(message = "need a vet")
        private Long vetId;

        @NotNull(message = "need a owner")
        private Long ownerId;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CONDITION {

        private List<Long> visitIds;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UPDATE {

        private LocalDate visitDate;

        private String description;
    }
}
