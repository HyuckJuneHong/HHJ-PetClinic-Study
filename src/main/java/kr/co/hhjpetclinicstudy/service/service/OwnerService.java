package kr.co.hhjpetclinicstudy.service.service;

import kr.co.hhjpetclinicstudy.infrastructure.error.exception.DuplicatedException;
import kr.co.hhjpetclinicstudy.infrastructure.error.exception.NotFoundException;
import kr.co.hhjpetclinicstudy.infrastructure.error.model.ResponseStatus;
import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import kr.co.hhjpetclinicstudy.service.model.mappers.OwnerMappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OwnerService {

    private final OwnerRepository ownerRepository;

    private final OwnerMappers ownerMappers;

    /**
     * owner create service
     * @param create : Info for Create an Owner
     */
    @Transactional
    public void createOwner(OwnerReqDTO.CREATE create) {

        final Owner owner = ownerMappers.toOwnerEntity(create);

        isTelephone(owner.getTelephone());

        ownerRepository.save(owner);
    }

    /**
     * owner get service
     * @param ownerId : id for get an owner
     * @return
     */
    public OwnerResDTO.READ getOwnerById(Long ownerId) {

        final Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        return ownerMappers.toReadDto(owner);
    }

    /**
     * owner update service
     * @param update : info for update an owner
     */
    @Transactional
    public void updateOwner(OwnerReqDTO.UPDATE update) {

        isTelephone(update.getTelephone());

        Owner owner = ownerRepository.findById(update.getOwnerId())
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        owner.updateOwner(update);
    }

    /**
     * owner delete service
     * @param ownerId : id for delete owner
     */
    @Transactional
    public void deleteOwnerById(Long ownerId) {

        final Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new NotFoundException(ResponseStatus.FAIL_NOT_FOUND));

        ownerRepository.delete(owner);
    }

    private void isTelephone(String telephone){

        if(ownerRepository.existsByTelephone(telephone))
            throw new DuplicatedException(ResponseStatus.FAIL_TELEPHONE_DUPLICATED);
    }
}
