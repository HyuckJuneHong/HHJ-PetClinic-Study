package kr.co.hhjpetclinicstudy.persistence.repository.search;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hhjpetclinicstudy.infrastructure.util.DynamicQueryUtils;
import kr.co.hhjpetclinicstudy.persistence.entity.Pet;
import kr.co.hhjpetclinicstudy.persistence.entity.QOwner;
import kr.co.hhjpetclinicstudy.persistence.entity.QPet;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PetSearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QOwner qOwner = QOwner.owner;

    private final QPet qPet = QPet.pet;

    public List<Pet> search(PetReqDTO.CONDITION condition) {

        return queryFactory
                .selectFrom(qPet)
                .join(qPet.owner, qOwner).fetchJoin()
                .where(
                        DynamicQueryUtils.filterCondition(condition.getPetIds(), qPet.id::in),
                        DynamicQueryUtils.generateEq(condition.getOwnerId(), qPet.owner.id::eq)
                )
                .fetch();
    }

    public Pet searchById(Long petId) {

        return queryFactory
                .selectFrom(qPet)
                .join(qPet.owner, qOwner).fetchJoin()
                .where(DynamicQueryUtils.generateEq(petId, qPet.id::eq))
                .fetchOne();
    }
}
