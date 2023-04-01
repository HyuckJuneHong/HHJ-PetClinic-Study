package kr.co.hhjpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.hhjpetclinicstudy.persistence.BaseEntity;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_vet")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AttributeOverride(
        name = "id",
        column = @Column(name = "vet_id", length = 4)
)
public class Vet extends BaseEntity {

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
        name = "vet_specialty",
        joinColumns = @JoinColumn(name = "vet_id"),
        inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private List<Specialty> specialtyList = new ArrayList<>();

    @Builder
    public Vet(String firstName,
               String lastName,
               List<Specialty> specialtyList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialtyList = specialtyList;
    }

    public static Vet dtoToEntity(VetReqDTO.CREATE create,
                                  List<Specialty> specialtyList) {
        return Vet.builder()
                .firstName(create.getFirstName())
                .lastName(create.getLastName())
                .specialtyList(specialtyList)
                .build();
    }
}
