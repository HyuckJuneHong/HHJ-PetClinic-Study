package kr.co.hhjpetclinicstudy.persistence.repository.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.entity.QOwner;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.IdsReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OwnerSearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QOwner qOwner = QOwner.owner;

    public List<Owner> search(IdsReqDTO condition) {

        return queryFactory
                .selectFrom(qOwner)
                .where(ownerIdsIn(condition.getConditionIds()))
                .fetch();
    }

    private BooleanExpression ownerIdsIn(List<Long> ownerIds) {

        return CollectionUtils.isEmpty(ownerIds) ? null : qOwner.id.in(ownerIds);
    }
}
