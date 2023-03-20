DROP TABLE IF EXISTS blog_statistics;
CREATE TABLE blog_statistics
(
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    type        VARCHAR(100) NOT NULL,
    keyword     VARCHAR(500) NOT NULL,
    count       BIGINT       NOT NULL,
    created_at  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    modified_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    PRIMARY KEY (id)
);
CREATE INDEX idx_blog_statistic_01 ON blog_statistics (keyword);
CREATE INDEX idx_blog_statistic_02 ON blog_statistics (count DESC);
