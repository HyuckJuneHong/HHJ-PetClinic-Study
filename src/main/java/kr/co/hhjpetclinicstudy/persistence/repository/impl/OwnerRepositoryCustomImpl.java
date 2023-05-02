package kr.co.hhjpetclinicstudy.persistence.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.entity.QOwner;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class OwnerRepositoryCustomImpl extends QuerydslRepositorySupport implements OwnerRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    private final QOwner qOwner = QOwner.owner;

    public OwnerRepositoryCustomImpl(JPAQueryFactory jpaQueryFactory) {

        super(Owner.class);

        this.jpaQueryFactory = jpaQueryFactory;
    }
}
