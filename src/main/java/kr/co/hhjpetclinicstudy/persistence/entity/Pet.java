package kr.co.hhjpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.hhjpetclinicstudy.persistence.BaseEntity;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.enums.PetType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(
        name = "tbl_pets",
        indexes = @Index(name = "i_pets", columnList = "pet_id")
)
@AttributeOverride(
        name = "id",
        column = @Column(name = "pet_id", length = 4)
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pet extends BaseEntity {

    @Column(name = "pet_name", length = 30)
    private String petName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "pet_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PetType petType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Builder
    private Pet(String petName,
                LocalDate birthDate,
                PetType petType,
                Owner owner) {

        this.petName = petName;
        this.birthDate = birthDate;
        this.petType = petType;
        this.owner = owner;
    }

    public void updatePetInfo(PetReqDTO.UPDATE update) {

        this.petName = update.getPetName();
        this.birthDate = update.getBirthDate();
        this.petType = PetType.of(update.getPetType());
    }
}
