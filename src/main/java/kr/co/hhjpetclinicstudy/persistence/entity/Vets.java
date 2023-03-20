package kr.co.hhjpetclinicstudy.persistence.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kr.co.hhjpetclinicstudy.persistence.BaseEntity;
import kr.co.hhjpetclinicstudy.service.model.enums.VetsSpecialties;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_vets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AttributeOverride(
        name = "id",
        column = @Column(name = "vets_id", length = 4)
)
public class Vets extends BaseEntity {
    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "vets_specialties", nullable = false)
    private VetsSpecialties vetsSpecialties;

    @Builder
    public Vets(String firstName,
                String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
