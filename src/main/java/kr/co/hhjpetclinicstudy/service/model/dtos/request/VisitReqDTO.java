package kr.co.hhjpetclinicstudy.service.model.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class VisitReqDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class CREATE{

        @NotNull(message = "Need a Visit date")
        private LocalDate visitDate;

        @NotBlank(message = "Need a Description")
        private String description;

        @NotNull(message = "need a pet")
        private Long petId;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class UPDATE {

        @NotNull(message = "Need a visit")
        private Long visitId;

        private LocalDate visitDate;

        private String description;
    }
}
