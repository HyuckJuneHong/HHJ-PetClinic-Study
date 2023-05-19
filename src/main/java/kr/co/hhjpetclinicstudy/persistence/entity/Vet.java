package kr.co.hhjpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.hhjpetclinicstudy.persistence.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "tbl_vets",
        indexes = @Index(name = "i_vets", columnList = "vet_id")
)
@AttributeOverride(
        name = "id",
        column = @Column(name = "vet_id", length = 4)
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vet extends BaseEntity {

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Builder
    private Vet(String firstName,
                String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }
}
