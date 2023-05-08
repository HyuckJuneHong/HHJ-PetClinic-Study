package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.search.OwnerSearchRepository;
import kr.co.hhjpetclinicstudy.service.model.OwnerCreators;
import kr.co.hhjpetclinicstudy.service.model.OwnerMapperImplTest;
import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.IdsReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import kr.co.hhjpetclinicstudy.service.model.mapper.OwnerMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;

import java.util.ArrayList;
import java.util.List;
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
    private OwnerSearchRepository ownerSearchRepository;

    @Mock
    private OwnerMapper ownersMapper;

    @Test
    @DisplayName("Owner 등록 - 성공")
    void createUser_success() {

        //given
        final OwnerReqDTO.CREATE create = OwnerCreators.ownerReqDto_create_creators();
        final Owner owner = OwnerMapperImplTest.toOwnerEntity(create);

        given(ownersMapper.toOwnerEntity(any(OwnerReqDTO.CREATE.class))).willReturn(owner);
        given(ownerRepository.existsByTelephone(any(String.class))).willReturn(false);

        //when, then
        assertDoesNotThrow(() -> ownerService.createOwner(create));
    }

    @Test
    @DisplayName("Owner 조회 - 성공")
    void getOwner_success(){

        //given
        List<OwnerResDTO.READ> reads = new ArrayList<>();
        reads.add(OwnerCreators.ownerResDTO_read_only_phone("010-1212-1212"));

        List<Owner> owners = new ArrayList<>();
        owners.add(OwnerCreators.owner_only_phone("010-1212-1212"));

        ArrayList<Long> ownersIds = new ArrayList<>();
        ownersIds.add(1L);
        IdsReqDTO condition = IdsReqDTO.builder().conditionIds(ownersIds).build();

        given(ownerSearchRepository.search(any())).willReturn(owners);
        given(ownersMapper.toReadDto(any(Owner.class))).willReturn(reads.get(0));

        //when
        List<OwnerResDTO.READ> findReads = ownerService.getOwnersByIds(condition);

        assertEquals(owners.get(0).getTelephone(), findReads.get(0).getTelephone());
    }

    @Test
    @DisplayName("Owner 수정 - 성공")
    void updateOwner_success() {

        //given
        Owner owner = OwnerCreators.owner_only_phone("010-1212-1212");

        final OwnerReqDTO.UPDATE update = OwnerCreators.ownerReqDTO_update_only_phone("010-1231-1231");

        given(ownerRepository.findById(any(Long.class))).willReturn(Optional.of(owner));
        given(ownerRepository.existsByTelephone(any(String.class))).willReturn(false);

        //when
        ownerService.updateOwner(1L, update);

        //then
        assertEquals(owner.getTelephone(), update.getTelephone());
    }

    @Test
    @DisplayName("Owner 삭제 - 성공")
    void deleteOwnerById_success() {

        // given
        List<Owner> ownerList = new ArrayList<>();
        ownerList.add(OwnerCreators.owner_only_phone("010-1212-1212"));

        ArrayList<Long> ownerIds = new ArrayList<>();
        ownerIds.add(1L);
        IdsReqDTO condition = IdsReqDTO.builder().conditionIds(ownerIds).build();

        given(ownerSearchRepository.search(any())).willReturn(ownerList);

        // when, then
        assertDoesNotThrow(() -> ownerService.deleteOwnersByIds(condition));
    }
}
