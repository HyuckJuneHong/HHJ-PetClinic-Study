package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.hhjpetclinicstudy.service.model.OwnerCreators;
import kr.co.hhjpetclinicstudy.service.model.OwnerMapperImpl;
import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import kr.co.hhjpetclinicstudy.service.model.mappers.OwnerMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/**
 * JUnit 5와 Mockito 프레임워크를 사용하여 OwnerService 클래스의 예외 처리에 대한 단위 테스트를 작성
 *
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
    private OwnerMapper ownerMappers;

    @Test
    @DisplayName("Owner 등록 - 성공")
    void createUser_success() {

        //given
        final OwnerReqDTO.CREATE create = OwnerCreators.ownerReqDto_create_creators();
        final Owner owner = OwnerMapperImpl.toOwnerEntity(create);

        given(ownerMappers.toOwnerEntity(any(OwnerReqDTO.CREATE.class))).willReturn(owner);
        given(ownerRepository.existsByTelephone(any(String.class))).willReturn(false);

        //when, then
        assertDoesNotThrow(() -> ownerService.createOwner(create));
    }

    @Test
    @DisplayName("Owner 조회 - 성공")
    void getOwnerById_success() {

        //given
        final OwnerReqDTO.CREATE create = OwnerCreators.ownerReqDto_create_creators();
        final Owner owner = OwnerMapperImpl.toOwnerEntity(create);
        final OwnerResDTO.READ read = OwnerMapperImpl.toReadDto(owner);

        given(ownerRepository.findById(any(Long.class))).willReturn(Optional.of(owner));
        given(ownerMappers.toReadDto(any(Owner.class))).willReturn(read);

        //when
        OwnerResDTO.READ findRead = ownerService.getOwnerById(1L);

        //then
        assertEquals(owner.getFirstName(), findRead.getFirstName());
        assertEquals(owner.getLastName(), findRead.getLastName());
        assertEquals(owner.getAddress(), findRead.getAddress());
        assertEquals(owner.getCity(), findRead.getCity());
        assertEquals(owner.getTelephone(), findRead.getTelephone());
    }

    @Test
    @DisplayName("Owner 수정 - 성공")
    void updateOwner_success() {

        //given
        final OwnerReqDTO.CREATE create = OwnerCreators.ownerReqDto_create_creators();
        Owner owner = OwnerMapperImpl.toOwnerEntity(create);

        final OwnerReqDTO.UPDATE update = OwnerCreators.ownerReqDto_update_creators("010-7777-7777");

        given(ownerRepository.findById(any(Long.class))).willReturn(Optional.of(owner));
        given(ownerRepository.existsByTelephone(any(String.class))).willReturn(false);

        //when
        ownerService.updateOwner(update);
        final Owner updatedOwner = ownerRepository.findById(1L)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        //then
        assertEquals(owner.getFirstName(), updatedOwner.getFirstName());
        assertEquals(owner.getLastName(), updatedOwner.getLastName());
        assertEquals(owner.getAddress(), updatedOwner.getAddress());
        assertEquals(owner.getCity(), updatedOwner.getCity());
        assertEquals(owner.getTelephone(), updatedOwner.getTelephone());
    }

    @Test
    @DisplayName("Owner 삭제 - 성공")
    void deleteOwnerById_success() {

        // given
        final OwnerReqDTO.CREATE create = OwnerCreators.ownerReqDto_create_creators();
        Owner owner = OwnerMapperImpl.toOwnerEntity(create);

        given(ownerRepository.findById(any(Long.class))).willReturn(Optional.of(owner));

        // when, then
        assertDoesNotThrow(() -> ownerService.deleteOwnerById(1L));
    }
}
