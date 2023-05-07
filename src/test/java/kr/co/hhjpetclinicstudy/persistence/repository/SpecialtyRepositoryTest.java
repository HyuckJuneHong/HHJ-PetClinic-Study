package kr.co.hhjpetclinicstudy.persistence.repository;

import kr.co.hhjpetclinicstudy.persistence.entity.Specialty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DataJpaTest
 * - 오직 JPA 컴포넌트들만을 테스트하기 위한 어노테이션
 * - full-auto config를 해제하고 JPA 테스트와 연관된 config만 적용
 *
 * SpringbootTest
 * - full application config을 로드해서 통합 테스트를 진행하기 위한 어노테이션
 *
 * @issue : engine[*]=InnoDB"; expected "identifier"; SQL statement:
 * - @DataJpaTest의 @AutoConfigureTestDatabase가 config에 있는 H2 DB가 아닌,
 * - EmbeddedDatabaseConnection에 설정된 임시의 H2 DB를 호출해서 문제 발생
 */
@DataJpaTest
public class SpecialtyRepositoryTest {

    @Autowired
    private SpecialtyRepository specialtyRepository;

//    @Test
//    public void findAllBySpecialtyNameIn_shouldReturnMatchingSpecialties() {
//
//        // given
//        Set<String> specialtyNames = Set.of("testSpec1", "testSpec2");
//
//        final Specialty specialty1 = Specialty.builder()
//                .specialtyName("testSpec1")
//                .build();
//        final Specialty specialty2 = Specialty.builder()
//                .specialtyName("testSpec2")
//                .build();
//        final Specialty specialty3 = Specialty.builder()
//                .specialtyName("testSpec3")
//                .build();
//
//        specialtyRepository.saveAll(List.of(specialty1, specialty2, specialty3));
//
//        // when
//        List<Specialty> specialties = specialtyRepository.findAllBySpecialtyNameIn(specialtyNames);
//
//        // then
//        assertThat(specialties)
//                .hasSize(2)
//                .contains(specialty1, specialty2);
//    }
//
//    @Test
//    void existsBySpecialtyName_shouldReturnTrueIfSpecialtyExists() {
//
//        //given
//        final Specialty specialty = Specialty.builder()
//                .specialtyName("testSpec")
//                .build();
//
//        specialtyRepository.save(specialty);
//
//        //when
//        boolean exists = specialtyRepository.existsBySpecialtyName("testSpec");
//
//        // then
//        assertThat(exists).isTrue();
//    }
}
