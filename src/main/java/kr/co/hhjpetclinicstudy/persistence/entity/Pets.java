package kr.co.hhjpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.hhjpetclinicstudy.persistence.BaseEntity;
import kr.co.hhjpetclinicstudy.service.model.enums.PetTypes;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_pets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AttributeOverride(
        name = "id",
        column = @Column(name = "pets_id", length = 4)
)
public class Pets extends BaseEntity {

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "birth_date")
    private LocalDate localDate;

    @Column(name = "pet_types", nullable = false)
    private PetTypes petTypes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owners_id")
    private Owners owners;

    @Builder
    public Pets(String name,
                LocalDate localDate,
                PetTypes petTypes,
                Owners owners) {
        this.name = name;
        this.localDate = localDate;
        this.petTypes = petTypes;
        this.owners = owners;
    }
}
