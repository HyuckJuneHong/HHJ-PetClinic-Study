package kr.co.hhjpetclinicstudy.persistence.repository;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    @DisplayName("전화번호가 존재하는 경우")
    public void existsByTelephone_TelephoneExists_ReturnsTrue() {

        //given
        String telephone = "010-1234-5678";

        //when
        boolean result = ownerRepository.existsByTelephone(telephone);

        //then
        assertTrue(result);
    }

    @Test
    @DisplayName("전화번호가 존재하지 않는 경우")
    public void existsByTelephone_TelephoneDoesNotExist_ReturnsFalse() {

        //given
        String telephone = "010-1111-2222";

        //when
        boolean result = ownerRepository.existsByTelephone(telephone);

        //then
        assertFalse(result);
    }
}
