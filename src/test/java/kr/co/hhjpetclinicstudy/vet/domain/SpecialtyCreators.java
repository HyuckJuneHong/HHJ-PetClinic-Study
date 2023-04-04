package kr.co.hhjpetclinicstudy.vet.domain;

import kr.co.hhjpetclinicstudy.persistence.entity.Specialty;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SpecialtyCreators {

    public static Specialty createSpecialty(String name1) {

        return Specialty.builder()
                .name(name1)
                .build();
    }

    public static List<Specialty> createSpecialties(String... names) {

        return Arrays.stream(names)
                .map(SpecialtyCreators::createSpecialty)
                .collect(Collectors.toList());
    }
}
