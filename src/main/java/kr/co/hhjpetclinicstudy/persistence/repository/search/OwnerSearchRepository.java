package kr.co.hhjpetclinicstudy.persistence.repository.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.entity.QOwner;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.co.hhjpetclinicstudy.infrastructure.util.DynamicQueryUtils.generateQueryCondition;

@Repository
@RequiredArgsConstructor
public class OwnerSearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QOwner qOwner = QOwner.owner;

    public List<Owner> search(OwnerReqDTO.CONDITION condition) {

        return queryFactory
                .selectFrom(qOwner)
                .where(
                        generateQueryCondition(condition.getOwnerIds(), qOwner.id::in)
                )
                .fetch();
    }

    public Owner searchById(Long ownerId) {

        return queryFactory
                .selectFrom(qOwner)
                .where(ownerIdEq(ownerId))
                .fetchOne();
    }

    private BooleanExpression ownerIdEq(Long ownerId) {

        return ownerId == null ? null : qOwner.id.eq(ownerId);
    }
}
