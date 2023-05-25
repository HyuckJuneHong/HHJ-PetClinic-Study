package kr.co.hhjpetclinicstudy.persistence.entity;

import jakarta.persistence.*;
import kr.co.hhjpetclinicstudy.persistence.BaseEntity;
import kr.co.hhjpetclinicstudy.service.model.enums.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "tbl_admins",
        indexes = @Index(name = "members", columnList = "member_id")
)
@AttributeOverride(
        name = "id",
        column = @Column(name = "member_id", length = 4)
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Column(name = "identity", length = 50, nullable = false, unique = true)
    private String identity;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "user_role", length = 20, nullable = false)
    private UserRole userRole;

    @Builder
    private Member(String identity,
                  String password,
                  String name,
                  UserRole userRole) {

        this.identity = identity;
        this.password = password;
        this.name = name;
        this.userRole = userRole;
    }
}
