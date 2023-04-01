package kr.co.hhjpetclinicstudy.service;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    /**
     * owner create service
     * @param create : Info for Create a Owner
     */
    public void createOwner(OwnerReqDTO.CREATE create) {

        final Owner owner = Owner.dtoToEntity(create);

        ownerRepository.save(owner);
    }

    /**
     * onwer get service
     * @param ownerId : id for get a owner
     * @return
     */
    public OwnerResDTO.READ getOwnerById(Long ownerId) {
        final Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Not Found Owner"));

        return Owner.entityToDto(owner);
    }
}
