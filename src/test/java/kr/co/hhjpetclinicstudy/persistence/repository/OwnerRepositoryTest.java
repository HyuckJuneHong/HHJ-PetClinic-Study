package kr.co.hhjpetclinicstudy.persistence.repository;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * @DataJpaTest : 스프링 부트 애플리케이션 컨텍스트의 일부만 로드되므로 레포지토리와 관련된 빈만 로드
 */
@DataJpaTest
public class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @BeforeEach
    public void setUp() {

        Owner owner = Owner.builder()
                .telephone("010-1234-5678")
                .build();

        ownerRepository.save(owner);
    }
}
