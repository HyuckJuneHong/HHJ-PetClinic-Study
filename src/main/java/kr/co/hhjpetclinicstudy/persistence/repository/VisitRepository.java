package kr.co.hhjpetclinicstudy.persistence.repository;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.entity.Pet;
import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.entity.Visit;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VisitResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @Query를 사용해 PetId로 조회하도록 하였다.
 * - 얻은 이점 : 객체 로딩하는 비용을 줄였다.
 * <p>
 * 이때 레포지토리에서 바로 DTO 형태로 매핑하도록 하는 방법은 어떨까?
 * - 이는 큰 성능 최적화를 준다는 장점이 있다.
 * - 즉, DB에서 select 프로젝션 데이터 까지 완전히 최적화 할 수 있다.
 * - 하지만, 범용성이 상당히 떨어진다는 단점이 있다...
 * - 예를 들어, 특정 API나 화면을 그리기 위한 데이터에 최적화되어버리고, 재사용이 거의 불가능하다.
 * <p>
 * 그렇다면 어떻게 성능 최적화를 지키면서 범용성을 높일까?
 * - 일단 먼저, 리포지토리에서 엔티티를 반환하도록 해서 범용성을 높이도록 개발하자.
 * - 그러다, 성능 최적화가 필요하면 리포지토리에서 엔티티를 반환하되, fetch join으로 최적화하자.
 * - 단 fetch join은 객체그래프가 어느정도 정해지므로 범용성이 약간 떨어진다.(약간~~)
 */
@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query("" +
            "select v " +
            "from Visit v " +
            "where v.pet.id = :petId")
    List<Visit> findByPetId(@Param("petId") Long petId);

    @Query("" +
            "select v " +
            "from Visit v " +
            "where v.owner.id = :ownerId")
    List<Visit> findByOwnerId(@Param("ownerId") Long ownerId);

    @Query("" +
            "select v " +
            "from Visit v " +
            "where v.vet.id = :vetId")
    List<Visit> findByVetId(@Param("vetId") Long vetId);
}
