package kr.co.hhjpetclinicstudy.persistence;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@MappedSuperclass
@EqualsAndHashCode(of = "id", callSuper = false)
@Getter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}
