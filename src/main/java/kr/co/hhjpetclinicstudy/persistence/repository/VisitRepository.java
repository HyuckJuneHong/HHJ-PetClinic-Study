package kr.co.hhjpetclinicstudy.persistence.repository;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.entity.Pet;
import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.entity.Visit;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VisitResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @Query를 사용해 PetId로 조회하도록 하였다.
 * - 얻은 이점 : 객체 로딩하는 비용을 줄였다.
 */
@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    List<Visit> findByPet(Pet pet);

    List<Visit> findByOwner(Owner owner);

    List<Visit> findByVet(Vet vet);

    @Query("" +
            "select v " +
            "from Visit v " +
            "where v.pet.id = :petId")
    List<Visit> findByPetId(Long petId);

    @Query("" +
            "select v " +
            "from Visit v " +
            "where v.owner.id = :ownerId")
    List<Visit> findByOwnerId(Long ownerId);

    @Query("" +
            "select v " +
            "from Visit v " +
            "where v.vet.id = :vetId")
    List<Visit> findByVetId(Long vetId);
}
