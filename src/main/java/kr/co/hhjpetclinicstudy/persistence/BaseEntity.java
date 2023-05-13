package kr.co.hhjpetclinicstudy.persistence;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@MappedSuperclass
@EqualsAndHashCode(of = "id", callSuper = false)
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    protected LocalDate createdAt;

    @LastModifiedDate
    @Column(name = "modified_at")
    protected LocalDate modifiedAt;
}
