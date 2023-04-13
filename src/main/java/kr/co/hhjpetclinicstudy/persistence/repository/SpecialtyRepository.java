package kr.co.hhjpetclinicstudy.persistence.repository;

import kr.co.hhjpetclinicstudy.persistence.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Query를사용하기 전
 * - 사용하기 전엔, specialtiesNames에 포함된 값과 일치하는 엔티티들을 조회하는 SQL을 자동생성했다.
 * - 하지만, 이는 실제 쿼리를 실행하기 전 메소드명 분석 후 SQL 쿼리를 생성하기 때문에 성능상 좋지 않다.
 * @Query를사용하면서 얻은 이점
 * - JPQL을 직접 사용했더니, 내가 직접 원하는 쿼리를 작성할 수 있게 되었다.
 * - 이로 인해, 쿼리 실행 속도와 복잡도를 제어할 수 있게 되었다.
 * <p>
 * 깨달은 점
 * - 단, JPQL을 직접 사용하다보니, 쿼리에 대한 오류가 발생할 수 있다는 것을 알았다.
 * - 즉, 오타나 문법에 대한 오류가 발생하기 쉽다.
 * - 또한 오류가 컴파일 시점에서 발생하지 않고 쿼리 실행 시점에서 발생하기 때문에, 디버깅하기도 어려웠다.
 * - 즉, 추후에는 컴파일 시점에서 오류를 잡을 수 있는 Querydsl을 사용해보자.
 */
@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {

    @Query("" +
            "select s " +
            "from Specialty s " +
            "where s.specialtyName in :spcialtiesNames")
    List<Specialty> findAllBySpecialtyNameIn(List<String> specialtiesNames);

    boolean existsBySpecialtyName(String specialtyName);
}
