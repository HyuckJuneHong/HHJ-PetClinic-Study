package kr.co.hhjpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.hhjpetclinicstudy.persistence.BaseEntity;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VetResDTO;
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

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "vet"
    )
    private List<VetSpecialty> vetSpecialties = new ArrayList<>();

    @Builder
    public Vet(String firstName,
               String lastName,
               List<VetSpecialty> vetSpecialties) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.vetSpecialties = vetSpecialties;
    }

    public static Vet dtoToEntity(VetReqDTO.CREATE create,
                                  List<VetSpecialty> vetSpecialties) {

        return Vet.builder()
                .firstName(create.getFirstName())
                .lastName(create.getLastName())
                .vetSpecialties(vetSpecialties)
                .build();
    }

    public static VetResDTO.READ entityToDto(Vet vet,
                                             List<String> specialtiesName) {

        return VetResDTO.READ.builder()
                .firstName(vet.getFirstName())
                .lastName(vet.getLastName())
                .specialtiesName(specialtiesName)
                .build();
    }

    public void updateVetSpecialties(List<VetSpecialty> vetSpecialties){

        this.vetSpecialties = vetSpecialties;
    }
}
