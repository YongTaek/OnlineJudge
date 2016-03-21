package kr.jadekim.oj.mainserver.repository;

import kr.jadekim.oj.mainserver.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p join p.board b where b.id=:board_id")
    List<Post> findByBoardId(@Param("board_id") int board_id, Pageable pageable);

}
