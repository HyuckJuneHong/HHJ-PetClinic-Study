package kr.co.hhjpetclinicstudy.service;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.repository.OwnerRepository;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
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
}
