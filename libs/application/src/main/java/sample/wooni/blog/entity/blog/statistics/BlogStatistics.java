package sample.wooni.blog.entity.blog.statistics;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.wooni.blog.entity.BaseTimeEntity;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "blog_statistics")
public class BlogStatistics extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Column(name = "count", nullable = false)
    private int count;

    @Builder
    public BlogStatistics(Long id,
                          String type,
                          String keyword,
                          int count) {
        this.id = id;
        this.type = type;
        this.keyword = keyword;
        this.count = count;
    }

    public void increaseCount() {
       this.count = this.count + 1;
    }
}
