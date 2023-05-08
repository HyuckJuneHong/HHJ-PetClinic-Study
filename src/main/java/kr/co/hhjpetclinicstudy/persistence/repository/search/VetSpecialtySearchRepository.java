package kr.co.hhjpetclinicstudy.persistence.repository.search;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hhjpetclinicstudy.persistence.entity.*;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class VetSpecialtySearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QVet qVet = QVet.vet;

    private final QSpecialty qSpecialty = QSpecialty.specialty;

    private final QVetSpecialty qVetSpecialty = QVetSpecialty.vetSpecialty;

    public List<VetSpecialty> searchAll() {

        return queryFactory
                .selectFrom(qVetSpecialty)
                .join(qVet).fetchJoin()
                .join(qSpecialty).fetchJoin()
                .fetch();
    }

    public List<VetSpecialty> searchAll(Vet vet,
                                        Set<String> specialtyNames) {

        return queryFactory
                .selectFrom(qVetSpecialty)
                .join(qVet).fetchJoin()
                .join(qSpecialty).fetchJoin()
                .where(
                        vetEq(vet),
                        specialtyNamesIn(specialtyNames)
                )
                .fetch();
    }

    public Long searchCountBySpecialtyName(String specialtyName) {

        return queryFactory
                .select(qVetSpecialty.count())
                .from(qVetSpecialty)
                .join(qVet).fetchJoin()
                .join(qSpecialty).fetchJoin()
                .where(specialtyNameEq(specialtyName))
                .fetchOne();
    }

    private BooleanExpression vetEq(Vet vet) {

        return qVetSpecialty.vet.eq(vet);
    }

    private BooleanExpression specialtyNamesIn(Set<String> specialtyNames) {

        return qVetSpecialty.specialty.specialtyName.in(specialtyNames);
    }

    private BooleanExpression specialtyNameEq(String specialtyNames) {

        return qVetSpecialty.specialty.specialtyName.eq(specialtyNames);
    }
}
