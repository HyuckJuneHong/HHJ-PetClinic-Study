package kr.co.hhjpetclinicstudy.persistence.repository.search;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hhjpetclinicstudy.infrastructure.util.DynamicQueryUtils;
import kr.co.hhjpetclinicstudy.persistence.entity.QVet;
import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VetSearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QVet qVet = QVet.vet;

    public List<Vet> search(VetReqDTO.CONDITION condition) {

        return queryFactory
                .selectFrom(qVet)
                .where(
                        DynamicQueryUtils.filterCondition(condition.getVetIds(), qVet.id::in)
                )
                .fetch();
    }
}
