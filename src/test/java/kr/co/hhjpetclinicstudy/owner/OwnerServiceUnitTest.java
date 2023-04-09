package kr.co.hhjpetclinicstudy.owner;

import kr.co.hhjpetclinicstudy.owner.model.OwnerCreators;
import kr.co.hhjpetclinicstudy.owner.model.OwnerMappersImpl;
import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.mappers.OwnerMappers;
import kr.co.hhjpetclinicstudy.service.service.OwnerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/**
 * JUnit 5와 Mockito 프레임워크를 사용하여 OwnerService 클래스의 예외 처리에 대한 단위 테스트를 작성
 * @ExtendWith(MockitoExtension.class) : MockitoExtension 클래스를 사용하여 Mockito 프레임워크를 JUnit 5에 통합.
 * @InjectMocks : 의존성 주입을 수행하는 Mockito 어노테이션.
 * @Mock : mock 객체를 생성하는 Mockito 어노테이션.
 */
@ExtendWith(MockitoExtension.class)
public class OwnerServiceUnitTest {

    @InjectMocks
    private OwnerService ownerService;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private OwnerMappers ownerMappers;

    @Test
    @DisplayName("Owner 등록 - 성공")
    void createUser_success() {

        //given
        final OwnerReqDTO.CREATE create = OwnerCreators.ownerReqDto_create_creators();
        final Owner owner = OwnerMappersImpl.toOwnerEntity(create);
        given(ownerMappers.toOwnerEntity(any(OwnerReqDTO.CREATE.class))).willReturn(owner);
        given(ownerRepository.save(any(Owner.class))).willReturn(owner);
        given(ownerRepository.existsByTelephone(any(String.class))).willReturn(false);

        //when, then
        assertDoesNotThrow(() -> ownerService.createOwner(create));
    }
}
