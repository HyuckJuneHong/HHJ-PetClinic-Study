package kr.co.hhjpetclinicstudy.owner.model;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import org.springframework.stereotype.Component;

public class OwnerMapperImpl {

    public static Owner toOwnerEntity(OwnerReqDTO.CREATE create) {
        if (create == null) {
            return null;
        } else {
            Owner.OwnerBuilder owner = Owner.builder();
            owner.firstName(create.getFirstName());
            owner.lastName(create.getLastName());
            owner.address(create.getAddress());
            owner.city(create.getCity());
            owner.telephone(create.getTelephone());
            return owner.build();
        }
    }

    public static OwnerResDTO.READ toReadDto(Owner owner) {
        if (owner == null) {
            return null;
        } else {
            OwnerResDTO.READ.READBuilder rEAD = OwnerResDTO.READ.builder();
            rEAD.firstName(owner.getFirstName());
            rEAD.lastName(owner.getLastName());
            rEAD.address(owner.getAddress());
            rEAD.city(owner.getCity());
            rEAD.telephone(owner.getTelephone());
            return rEAD.build();
        }
    }
}
