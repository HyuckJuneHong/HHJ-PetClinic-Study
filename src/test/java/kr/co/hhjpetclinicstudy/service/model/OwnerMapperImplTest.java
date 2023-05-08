package kr.co.hhjpetclinicstudy.service.model;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;

public class OwnerMapperImplTest {

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
}
