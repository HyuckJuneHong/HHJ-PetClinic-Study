package kr.co.hhjpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.hhjpetclinicstudy.persistence.BaseEntity;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "tbl_owners",
        indexes = @Index(name = "i_owners", columnList = "owner_id")
)
@AttributeOverride(
        name = "id",
        column = @Column(name = "owner_id", length = 4)
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner extends BaseEntity {

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "city", length = 80)
    private String city;

    @Column(name = "telephone", length = 20, nullable = false, unique = true)
    private String telephone;

    @Builder
    private Owner(String firstName,
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

    public void updateOwner(OwnerReqDTO.UPDATE update) {

        this.firstName = update.getFirstName();
        this.lastName = update.getLastName();
        this.address = update.getAddress();
        this.city = update.getCity();
        this.telephone = update.getTelephone();
    }
}
