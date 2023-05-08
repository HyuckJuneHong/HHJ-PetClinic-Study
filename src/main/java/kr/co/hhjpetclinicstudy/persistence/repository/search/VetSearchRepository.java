package kr.co.hhjpetclinicstudy.persistence.repository.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hhjpetclinicstudy.persistence.entity.*;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VetSearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QVet qVet = QVet.vet;

    private final QSpecialty qSpecialty = QSpecialty.specialty;

    private final QVetSpecialty qVetSpecialty = QVetSpecialty.vetSpecialty;

    /**
     * [distinct() 를 사용하는 이유]
     * - Vet A가 Specialty B, C, D를 가지고 있다고 가정.
     * - VetSpecialty 테이블에는 A-B, A-C, A-D의 데이터가 저장.
     * - 따라서 Vet A가 조회될 때 VetSpecialty와 Join하게 되면 결과로는 3개의 레코드가 나온다.
     * - 결과적으로 Vet도 3번 중복 조회.
     * - 이 경우 distinct() 키워드를 사용하여 중복을 제거할 수 있기 때문.
     */
    public List<Vet> search(VetReqDTO.CONDITION condition) {

        return queryFactory
                .selectDistinct(qVet)
                .from(qVet)
                .join(qVetSpecialty).fetchJoin()
                .join(qSpecialty).fetchJoin()
                .where(
                        vetIdsIn(condition.getVetIds()),
                        vetIdEq(condition.getVetId())
                )
                .fetch();
    }

    private BooleanExpression vetIdsIn(List<Long> vetIds) {

        return CollectionUtils.isEmpty(vetIds) ? null : qVet.id.in(vetIds);
    }

    private BooleanExpression vetIdEq(Long vetId) {

        return vetId == null ? null : qVet.id.eq(vetId);
    }
}
