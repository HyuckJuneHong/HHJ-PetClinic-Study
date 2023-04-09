package kr.co.hhjpetclinicstudy.owner;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.DuplicatedException;
import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/**
 * JUnit 5와 Mockito 프레임워크를 사용하여 OwnerService 클래스의 예외 처리에 대한 단위 테스트를 작성
 * @ExtendWith(MockitoExtension.class) : MockitoExtension 클래스를 사용하여 Mockito 프레임워크를 JUnit 5에 통합.
 * @InjectMocks : 의존성 주입을 수행하는 Mockito 어노테이션.
 * @Mock : mock 객체를 생성하는 Mockito 어노테이션.
 */
@ExtendWith(MockitoExtension.class)
public class OwnerServiceExceptionUnitTest {

    @InjectMocks
    private OwnerService ownerService;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private OwnerMappers ownerMappers;

    @Test
    @DisplayName("Owner 등록 실패 - 전화번호 중복 - DuplicatedException")
    public void createOwner_error_telephone_DuplicatedException() {

        //given
        final OwnerReqDTO.CREATE create = OwnerCreators.ownerReqDto_create_creators();
        final Owner owner = OwnerMappersImpl.toOwnerEntity(create);

        given(ownerRepository.existsByTelephone(any(String.class))).willReturn(true);
        given(ownerMappers.toOwnerEntity(any(OwnerReqDTO.CREATE.class))).willReturn(owner);

        //when, then
        DuplicatedException exception = assertThrows(DuplicatedException.class, () -> ownerService.createOwner(create));
        assertEquals(ResponseStatus.FAIL_TELEPHONE_DUPLICATED.getMessage(), exception.getMessage());
    }

    @Test
    @DisplayName("Owner 등록 실패 - 필수 입력 값 미입력 - NullPointerException")
    public void createOwner_error_null_NullPointerException() {

        //given
        final OwnerReqDTO.CREATE create = OwnerReqDTO.CREATE.builder().build();

        //when, then
        NullPointerException exception = assertThrows(NullPointerException.class, () -> ownerService.createOwner(create));
        assertNotNull(exception);
    }

    @Test
    @DisplayName("Owner 조회 실패 - 해당 ID에 대한 조회 실패 - NotFoundException")
    public void getOwnerById_error_NotFoundException(){

        //given
        given(ownerRepository.findById(any(Long.class))).willReturn(Optional.empty());

        //when, then
        assertThrows(NotFoundException.class, () -> ownerService.getOwnerById(1L));
    }
}
