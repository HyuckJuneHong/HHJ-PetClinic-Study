package kr.co.hhjpetclinicstudy.infrastructure.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaQuerydslConfig {

    /**
     * 굳이 @PersistenceContext 를 사용한 이유?
     * - 빈이란 것은 하나의 싱글톤 객체로 생성되어 재사용된다.
     * - 하지만, EntityManager 는 커넥션 풀 관리 등의 역할을 하고 있어서 재사용하거나 스레드끼리 공유하면 안된다.
     * - 때문에, @PersistenceContext 를 선언하여 중간에 프록시가 새 EntityManager 를 생성하게 한다.
     * - 이로 인해 thread-safe 를 보장한다.
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {

        return new JPAQueryFactory(entityManager);
    }
}
