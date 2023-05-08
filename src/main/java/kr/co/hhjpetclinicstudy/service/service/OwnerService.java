package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.DuplicatedException;
import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.hhjpetclinicstudy.persistence.repository.search.OwnerSearchRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.IdsReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import kr.co.hhjpetclinicstudy.service.model.mapper.OwnerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OwnerService {

    private final OwnerRepository ownerRepository;

    private final OwnerSearchRepository ownerSearchRepository;

    private final OwnerMapper ownerMapper;

    /**
     * owner create service
     * @param create : Info for Create an Owner
     */
    @Transactional
    public void createOwner(OwnerReqDTO.CREATE create) {

        final Owner owner = ownerMapper.toOwnerEntity(create);

        isTelephone(owner.getTelephone());

        ownerRepository.save(owner);
    }

    public List<OwnerResDTO.READ> getOwnersByIds(IdsReqDTO ownerIds) {

        final List<Owner> owners = ownerSearchRepository.search(ownerIds);

        return owners.stream()
                .map(ownerMapper::toReadDto)
                .collect(Collectors.toList());
    }

    /**
     * owner update service
     * @param update : info for update an owner
     */
    @Transactional
    public void updateOwner(Long ownerId,
                            OwnerReqDTO.UPDATE update) {

        Owner owner = ownerRepository
                .findById(ownerId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        isTelephone(owner.getTelephone(), update.getTelephone());

        owner.updateOwner(update);
    }

    @Transactional
    public void deleteOwnersByIds(IdsReqDTO ownerIds) {

        final List<Owner> owners = ownerSearchRepository.search(ownerIds);

        ownerRepository.deleteAll(owners);
    }

    public void isTelephone(String telephone){

        if(ownerRepository.existsByTelephone(telephone))
            throw new DuplicatedException(ResponseStatus.FAIL_TELEPHONE_DUPLICATED);
    }

    public void isTelephone(String telephone,
                             String updateTelephone){

        if(!telephone.equals(updateTelephone)){
            isTelephone(updateTelephone);
        }
    }
}
