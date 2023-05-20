package kr.co.hhjpetclinicstudy.persistence.repository.search;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hhjpetclinicstudy.infrastructure.util.DynamicQueryUtils;
import kr.co.hhjpetclinicstudy.persistence.entity.*;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
                .join(qVisit.owner, qOwner).fetchJoin()
                .join(qVisit.pet, qPet).fetchJoin()
                .join(qVisit.vet, qVet).fetchJoin()
                .where(
                        DynamicQueryUtils.filterCondition(condition.getVisitIds(), qVisit.id::in)
                )
                .fetch();
    }

    public Visit searchById(Long visitId) {

        return queryFactory
                .selectFrom(qVisit)
                .join(qVisit.owner, qOwner).fetchJoin()
                .join(qVisit.pet, qPet).fetchJoin()
                .join(qVisit.vet, qVet).fetchJoin()
                .where(DynamicQueryUtils.generateEq(visitId, qVisit.id::eq))
                .fetchOne();
    }

    public List<Visit> searchAllByOwner(Long ownerId) {

        return queryFactory
                .selectFrom(qVisit)
                .join(qVisit.owner, qOwner).fetchJoin()
                .join(qVisit.pet, qPet).fetchJoin()
                .join(qVisit.vet, qVet).fetchJoin()
                .where(DynamicQueryUtils.generateEq(ownerId, qOwner.id::eq))
                .fetch();
    }

    public List<Visit> searchAllByPet(Long petId) {

        return queryFactory
                .selectFrom(qVisit)
                .join(qVisit.owner, qOwner).fetchJoin()
                .join(qVisit.pet, qPet).fetchJoin()
                .join(qVisit.vet, qVet).fetchJoin()
                .where(DynamicQueryUtils.generateEq(petId, qPet.id::eq))
                .fetch();
    }

    public List<Visit> searchAllByVet(Long vetId) {

        return queryFactory
                .selectFrom(qVisit)
                .join(qVisit.owner, qOwner).fetchJoin()
                .join(qVisit.pet, qPet).fetchJoin()
                .join(qVisit.vet, qVet).fetchJoin()
                .where(DynamicQueryUtils.generateEq(vetId, qVet.id::eq))
                .fetch();
    }
}
