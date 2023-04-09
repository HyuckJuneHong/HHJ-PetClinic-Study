package kr.co.hhjpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.hhjpetclinicstudy.persistence.BaseEntity;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_visits")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AttributeOverride(
        name = "id",
        column = @Column(name = "visits_id", length = 4)
)
public class Visit extends BaseEntity {

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Builder
    private Visit(LocalDate visitDate,
                 String description,
                 Pet pet) {

        this.visitDate = visitDate;
        this.description = description;
        this.pet = pet;
    }

    public void updateVisit(VisitReqDTO.UPDATE update) {

        this.visitDate = update.getVisitDate();
        this.description = update.getDescription();
    }
}
