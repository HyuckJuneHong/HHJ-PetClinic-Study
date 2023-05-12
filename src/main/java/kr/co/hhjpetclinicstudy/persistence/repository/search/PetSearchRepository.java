package kr.co.hhjpetclinicstudy.persistence.repository.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hhjpetclinicstudy.persistence.entity.Pet;
import kr.co.hhjpetclinicstudy.persistence.entity.QOwner;
import kr.co.hhjpetclinicstudy.persistence.entity.QPet;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

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
                .join(qOwner).fetchJoin()
                .where(
                        petIdsIn(condition.getPetIds()),
                        ownerIdEq(condition.getOwnerId())
                )
                .fetch();
    }

    public Pet searchById(Long petId) {

        return queryFactory
                .selectFrom(qPet)
                .join(qOwner).fetchJoin()
                .where(petIdEq(petId))
                .fetchOne();
    }

    private BooleanExpression petIdsIn(List<Long> petIds) {

        return CollectionUtils.isEmpty(petIds) ? null : qPet.id.in(petIds);
    }

    private BooleanExpression petIdEq(Long petId) {

        return petId == null ? null : qPet.id.eq(petId);
    }

    private BooleanExpression ownerIdEq(Long ownerId) {

        return ownerId == null ? null : qPet.owner.id.eq(ownerId);
    }
}
