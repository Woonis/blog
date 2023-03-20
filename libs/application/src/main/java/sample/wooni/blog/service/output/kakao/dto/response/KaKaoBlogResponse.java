package sample.wooni.blog.service.output.kakao.dto.response;

import java.beans.ConstructorProperties;
import java.util.List;

public record KaKaoBlogResponse(
        KaKaoMeta meta,
        List<KaKaoBlogDetail> documents
) {
    @ConstructorProperties({"meta", "documents"})
    public KaKaoBlogResponse {
    }
}
