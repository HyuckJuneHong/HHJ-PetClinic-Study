package kr.co.hhjpetclinicstudy.persistence.repository.custom;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerSearchRepository {

    List<Owner> search(OwnerReqDTO.CONDITION condition);
}
