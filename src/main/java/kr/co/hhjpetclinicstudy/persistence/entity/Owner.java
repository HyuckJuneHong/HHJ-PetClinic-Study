package kr.co.hhjpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.hhjpetclinicstudy.persistence.BaseEntity;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_owner")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AttributeOverride(
        name = "id",
        column = @Column(name = "owner_id", length = 4)
)
public class Owner extends BaseEntity {

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "city", length = 80)
    private String city;

    @Column(name = "telephone", unique = true, length = 20)
    private String telephone;

    @Builder
    public Owner(String firstName,
                 String lastName,
                 String address,
                 String city,
                 String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }

    public static Owner dtoToEntity(OwnerReqDTO.CREATE create) {
        return Owner.builder()
                .firstName(create.getFirstName())
                .lastName(create.getLastName())
                .address(create.getAddress())
                .city(create.getCity())
                .telephone(create.getTelephone())
                .build();
    }

    public static OwnerResDTO.READ entityToDto(Owner owner) {
        return OwnerResDTO.READ.builder()
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .address(owner.address)
                .city(owner.city)
                .telephone(owner.telephone)
                .build();
    }
}
