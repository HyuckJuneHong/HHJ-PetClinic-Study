package kr.co.hhjpetclinicstudy.persistence.repository;

import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.entity.VetSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface VetSpecialtyRepository extends JpaRepository<VetSpecialty, Long> {

    Set<VetSpecialty> findByVetAndSpecialty_SpecialtyNameIn(Vet vet, Set<String> specialtiesNames);

    @Query("" +
            "select count(vs) " +
            "from VetSpecialty vs " +
            "where vs.specialty.specialtyName = :specialtiesNames")
    int countBySpecialtyName(@Param("specialtiesNames") String specialtiesNames);
}
