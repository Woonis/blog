package sample.wooni.blog.service.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import sample.wooni.blog.service.entity.BlogStatistics;
import sample.wooni.blog.service.search.dto.BlogSearchType;

import java.util.List;

import static sample.wooni.blog.service.entity.QBlogStatistics.blogStatistics;

@Repository
public class BlogStatisticsJapRepository implements BlogStatisticsRepository {

    private final JPAQueryFactory queryFactory;

    public BlogStatisticsJapRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<BlogStatistics> fetchTop10Keyword(BlogSearchType type) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(blogStatistics.type.eq(type.name()));

        return queryFactory
                .selectFrom(blogStatistics)
                .where(builder)
                .orderBy(blogStatistics.count.desc(), blogStatistics.id.desc())
                .limit(10)
                .fetch();
    }
}
