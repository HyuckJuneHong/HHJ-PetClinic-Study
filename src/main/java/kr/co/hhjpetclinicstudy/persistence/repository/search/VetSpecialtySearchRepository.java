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

    public List<VetSpecialty> searchAllByVetId(Long vetId){

        return queryFactory
                .selectDistinct(qVetSpecialty)
                .from(qVetSpecialty)
                .join(qVetSpecialty.vet, qVet).fetchJoin()
                .join(qVetSpecialty.specialty, qSpecialty).fetchJoin()
                .where(
                        DynamicQueryUtils.generateEq(vetId, qVetSpecialty.vet.id::eq)
                )
                .fetch();
    }

    public List<VetSpecialty> searchAll() {

        return queryFactory
                .selectDistinct(qVetSpecialty)
                .from(qVetSpecialty)
                .join(qVetSpecialty.vet, qVet).fetchJoin()
                .join(qVetSpecialty.specialty, qSpecialty).fetchJoin()
                .fetch();
    }

    public List<VetSpecialty> searchAll(List<Vet> vets) {

        return queryFactory
                .selectDistinct(qVetSpecialty)
                .from(qVetSpecialty)
                .join(qVetSpecialty.vet, qVet).fetchJoin()
                .join(qVetSpecialty.specialty, qSpecialty).fetchJoin()
                .where(
                        DynamicQueryUtils.filterCondition(vets, qVetSpecialty.vet::in)
                )
                .fetch();
    }

    public List<VetSpecialty> searchAll(Vet vet,
                                        Set<String> specialtyNames) {

        return queryFactory
                .selectDistinct(qVetSpecialty)
                .from(qVetSpecialty)
                .join(qVetSpecialty.vet, qVet).fetchJoin()
                .join(qVetSpecialty.specialty, qSpecialty).fetchJoin()
                .where(
                        DynamicQueryUtils.generateEq(vet, qVetSpecialty.vet::eq),
                        DynamicQueryUtils.filterCondition(specialtyNames, qVetSpecialty.specialty.specialtyName::in)
                )
                .fetch();
    }
}
