package sample.wooni.blog.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import sample.wooni.blog.RepositoryModule;
import sample.wooni.blog.entity.EntityModule;

@Configuration
@EntityScan(basePackageClasses = EntityModule.class)
@EnableJpaRepositories(basePackageClasses = RepositoryModule.class)
public class BlogJpaConfig {
    @PersistenceContext
    private EntityManager entityManager;
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(this.entityManager);
    }
}
