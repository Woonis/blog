package sample.wooni.blog.statistics;


import org.springframework.data.jpa.repository.JpaRepository;
import sample.wooni.blog.entity.blog.statistics.BlogStatistics;
import sample.wooni.blog.service.search.dto.BlogSearchType;

import java.util.List;

public interface BlogStatisticsJpaRepository extends JpaRepository<BlogStatistics, Long>, BlogStatisticsCustomRepository{
}
