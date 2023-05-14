package kr.co.hhjpetclinicstudy.persistence.repository.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hhjpetclinicstudy.persistence.entity.*;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static kr.co.hhjpetclinicstudy.infrastructure.util.DynamicQueryUtils.generateQueryCondition;

@Repository
@RequiredArgsConstructor
public class VisitSearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QVisit qVisit = QVisit.visit;

    private final QPet qPet = QPet.pet;

    private final QVet qVet = QVet.vet;

    private final QOwner qOwner = QOwner.owner;

    public List<Visit> search(VisitReqDTO.CONDITION condition) {

        return queryFactory
                .selectFrom(qVisit)
                .join(qPet).fetchJoin()
                .join(qVet).fetchJoin()
                .join(qOwner).fetchJoin()
                .where(
                        generateQueryCondition(condition.getVisitIds(), qVisit.id::in)
                )
                .fetch();
    }

    public Visit searchById(Long visitId) {

        return queryFactory
                .selectFrom(qVisit)
                .join(qPet).fetchJoin()
                .join(qVet).fetchJoin()
                .join(qOwner).fetchJoin()
                .where(visitIdEq(visitId))
                .fetchOne();
    }

    public List<Visit> searchAllByOwner(Long ownerId) {

        return queryFactory
                .selectFrom(qVisit)
                .join(qPet).fetchJoin()
                .join(qVet).fetchJoin()
                .join(qOwner).fetchJoin()
                .where(ownerIdEq(ownerId))
                .fetch();
    }

    public List<Visit> searchAllByPet(Long petId) {

        return queryFactory
                .selectFrom(qVisit)
                .join(qPet).fetchJoin()
                .join(qVet).fetchJoin()
                .join(qOwner).fetchJoin()
                .where(petIdEq(petId))
                .fetch();
    }

    public List<Visit> searchAllByVet(Long vetId) {

        return queryFactory
                .selectFrom(qVisit)
                .join(qPet).fetchJoin()
                .join(qVet).fetchJoin()
                .join(qOwner).fetchJoin()
                .where(vetIdEq(vetId))
                .fetch();
    }

    private BooleanExpression visitIdEq(Long visitId) {

        return visitId == null ? null : qVisit.id.eq(visitId);
    }

    private BooleanExpression ownerIdEq(Long ownerId) {

        return ownerId == null ? null : qVisit.owner.id.eq(ownerId);
    }

    private BooleanExpression petIdEq(Long petId) {

        return petId == null ? null : qVisit.pet.id.eq(petId);
    }

    private BooleanExpression vetIdEq(Long vetId) {

        return vetId == null ? null : qVisit.vet.id.eq(vetId);
    }
}
