package kr.co.hhjpetclinicstudy.persistence.repository;

import kr.co.hhjpetclinicstudy.persistence.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

    List<Specialty> findAllBySpecialtyName(List<String> specialtiesNames);

    boolean existsBySpecialtyName(String specialtyName);
}
