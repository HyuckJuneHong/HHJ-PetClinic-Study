package kr.co.hhjpetclinicstudy.persistence.repository.custom;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.entity.QOwner;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class OwnerSearchRepositoryImpl extends QuerydslRepositorySupport implements OwnerSearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QOwner qOwner = QOwner.owner;

    public OwnerSearchRepositoryImpl(JPAQueryFactory queryFactory) {

        super(Owner.class);

        this.queryFactory = queryFactory;
    }

    @Override
    public List<Owner> search(OwnerReqDTO.CONDITION condition) {

        return queryFactory
                .selectFrom(qOwner)
                .where(
                        ownerIdIn(condition.getOwnerIds())
                )
                .fetch();
    }

    private BooleanExpression ownerIdIn(List<Long> ownerIds) {

        return CollectionUtils.isEmpty(ownerIds) ? null : qOwner.id.in(ownerIds);
    }
}
