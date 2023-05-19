package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.persistence.entity.Specialty;

import java.util.HashSet;
import java.util.Set;

public interface IVetSpecialtyService {

    HashSet<Specialty> getOrCreateSpecialtiesByNames(Set<String> specialtiesNames);

    void deleteBySpecialtiesWithoutVet(Set<String> specialtiesName);
}
