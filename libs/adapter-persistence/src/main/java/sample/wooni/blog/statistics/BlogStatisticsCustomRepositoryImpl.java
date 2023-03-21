package sample.wooni.blog.statistics;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Repository;
import sample.wooni.blog.entity.blog.statistics.BlogStatistics;
import sample.wooni.blog.service.search.dto.BlogSearchType;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static sample.wooni.blog.entity.blog.statistics.QBlogStatistics.blogStatistics;


@Repository
public class BlogStatisticsCustomRepositoryImpl implements BlogStatisticsCustomRepository {
    private final JPAQueryFactory queryFactory;

    public BlogStatisticsCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<BlogStatistics> findOneBy(BlogSearchType type, String keyword) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(blogStatistics.keyword.eq(keyword));

        if (Objects.nonNull(type) && type.isCreatableType()) {
            builder.and(blogStatistics.type.eq(type.name()));
        }

        return Optional.ofNullable(
                queryFactory
                        .selectFrom(blogStatistics)
                        .where(builder)
                        .limit(NumberUtils.INTEGER_ONE)
                        .fetchOne()
        );
    }

    @Override
    public List<BlogStatistics> fetchTopKeyword(BlogSearchType type, int limit) {
        BooleanBuilder builder = new BooleanBuilder();

        if (Objects.nonNull(type) && type.isCreatableType()) {
            builder.and(blogStatistics.type.eq(type.name()));
        }

        return queryFactory
                .selectFrom(blogStatistics)
                .where(builder)
                .orderBy(blogStatistics.count.desc(), blogStatistics.id.desc())
                .limit(limit)
                .fetch();
    }
}
