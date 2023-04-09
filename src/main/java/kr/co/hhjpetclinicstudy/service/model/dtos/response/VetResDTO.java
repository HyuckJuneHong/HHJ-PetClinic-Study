package kr.co.hhjpetclinicstudy.service.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class VetResDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class READ {

        private String firstName;

        private String lastName;

        private List<String> specialtiesName;
    }
}
