package sample.wooni.blog.statistics;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import sample.wooni.blog.entity.blog.statistics.BlogStatistics;
import sample.wooni.blog.service.search.dto.BlogSearchType;

import java.util.List;
import java.util.Objects;

import static sample.wooni.blog.entity.blog.statistics.QBlogStatistics.blogStatistics;


@Repository
public class BlogStatisticsJapRepositoryImpl implements BlogStatisticsJpaRepository {

    public static final int INT_TEN = 10;
    private final JPAQueryFactory queryFactory;

    public BlogStatisticsJapRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<BlogStatistics> fetchTop10Keyword(BlogSearchType type) {
        BooleanBuilder builder = new BooleanBuilder();

        if (Objects.nonNull(type) && BlogSearchType.ALL != type) {
            builder.and(blogStatistics.type.eq(type.name()));
        }

        return queryFactory
                .selectFrom(blogStatistics)
                .where(builder)
                .orderBy(blogStatistics.count.desc(), blogStatistics.id.desc())
                .limit(INT_TEN)
                .fetch();
    }
}
