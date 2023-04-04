package kr.co.hhjpetclinicstudy.service;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OwnerService {

    private final OwnerRepository ownerRepository;

    /**
     * owner create service
     * @param create : Info for Create an Owner
     */
    @Transactional
    public void createOwner(OwnerReqDTO.CREATE create) {

        final Owner owner = Owner.dtoToEntity(create);

        ownerRepository.save(owner);
    }

    /**
     * owner get service
     * @param ownerId : id for get an owner
     * @return
     */
    public OwnerResDTO.READ getOwnerById(Long ownerId) {

        final Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Not Found Owner"));

        return Owner.entityToDto(owner);
    }

    /**
     * owner update service
     * @param update : info for update an owner
     */
    @Transactional
    public void updateOwner(OwnerReqDTO.UPDATE update) {

        Owner owner = ownerRepository.findById(update.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Not Found Owner"));

        owner.updateOwner(update);

        ownerRepository.save(owner);
    }

    /**
     * owner delete service
     * @param ownerId : id for delete owner
     */
    @Transactional
    public void deleteOwnerById(Long ownerId) {

        final Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Not Found Owner"));

        ownerRepository.delete(owner);
    }
}
