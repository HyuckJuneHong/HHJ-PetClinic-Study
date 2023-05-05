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

    public List<Vet> search(VetReqDTO.CONDITION condition) {

        return queryFactory
                .select(qVet)
                .from(qVet)
                .innerJoin(qVet.vetSpecialties, qVetSpecialty).fetchJoin()
                .innerJoin(qVetSpecialty.specialty, qSpecialty).fetchJoin()
                .where(vetIdsIn(condition.getVetIds()))
                .fetch();
    }

    private BooleanExpression vetIdsIn(List<Long> vetIds) {

        return CollectionUtils.isEmpty(vetIds) ? null : qVet.id.in(vetIds);
    }
}
