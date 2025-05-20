package ssafy.project07.repository.youtube;

import ssafy.project07.domain.YoutubeVideo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface YoutubeVideoRepository extends JpaRepository<YoutubeVideo, Long> {

    // 카테고리별로 조회수 순으로 정렬하여 조회
    @Query("SELECT v FROM YoutubeVideo v WHERE v.category = :category ORDER BY v.viewCount DESC")
    List<YoutubeVideo> findByCategoryOrderByViewCountDesc(@Param("category") String category);

    // 특정 카테고리의 모든 항목 삭제
    void deleteByCategoryEquals(String category);
}