package kr.co.hhjpetclinicstudy.persistence.repository.search;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hhjpetclinicstudy.infrastructure.util.DynamicQueryUtils;
import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.entity.QOwner;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OwnerSearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QOwner qOwner = QOwner.owner;

    public List<Owner> search(OwnerReqDTO.CONDITION condition) {

        return queryFactory
                .selectFrom(qOwner)
                .where(
                        DynamicQueryUtils.filterCondition(condition.getOwnerIds(), qOwner.id::in)
                )
                .fetch();
    }

    public Owner searchById(Long ownerId) {

        return queryFactory
                .selectFrom(qOwner)
                .where(DynamicQueryUtils.generateEq(ownerId, qOwner.id::eq))
                .fetchOne();
    }
}
