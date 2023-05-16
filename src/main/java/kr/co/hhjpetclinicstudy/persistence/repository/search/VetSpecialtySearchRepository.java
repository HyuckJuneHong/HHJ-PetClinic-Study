package kr.co.hhjpetclinicstudy.persistence.repository.search;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hhjpetclinicstudy.infrastructure.util.DynamicQueryUtils;
import kr.co.hhjpetclinicstudy.persistence.entity.*;
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
                        DynamicQueryUtils.generateEq(vet, qVetSpecialty.vet::eq),
                        DynamicQueryUtils.filterCondition(specialtyNames, qVetSpecialty.specialty.specialtyName::in)
                )
                .fetch();
    }

    public Long searchCountBySpecialtyName(String specialtyName) {

        return queryFactory
                .select(qVetSpecialty.count())
                .from(qVetSpecialty)
                .join(qVet).fetchJoin()
                .join(qSpecialty).fetchJoin()
                .where(DynamicQueryUtils.generateEq(specialtyName, qVetSpecialty.specialty.specialtyName::eq))
                .fetchOne();
    }
}
