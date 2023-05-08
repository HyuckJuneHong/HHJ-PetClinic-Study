package kr.co.hhjpetclinicstudy.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
}
