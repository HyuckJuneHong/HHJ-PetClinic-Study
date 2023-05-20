package kr.co.hhjpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.hhjpetclinicstudy.persistence.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "tbl_specialties",
        indexes = @Index(name = "i_specialties", columnList = "specialty_id")
)
@AttributeOverride(
        name = "id",
        column = @Column(name = "specialty_id", length = 4)
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Specialty extends BaseEntity {

    @Column(name = "specialty_name", length = 80)
    private String specialtyName;

    @Builder
    private Specialty(String specialtyName) {

        this.specialtyName = specialtyName;
    }
}
