package kr.co.hhjpetclinicstudy.persistence.repository.search;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.hhjpetclinicstudy.persistence.entity.QOwner;
import kr.co.hhjpetclinicstudy.persistence.entity.QPet;
import kr.co.hhjpetclinicstudy.persistence.entity.QVet;
import kr.co.hhjpetclinicstudy.persistence.entity.QVisit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class VisitSearchRepository {

    private final JPAQueryFactory queryFactory;

    private final QVisit qVisit = QVisit.visit;

    private final QPet qPet = QPet.pet;

    private final QVet qVet = QVet.vet;

    private final QOwner qOwner = QOwner.owner;
}
