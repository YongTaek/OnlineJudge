package kr.jadekim.oj.mainserver.repository;

import kr.jadekim.oj.mainserver.entity.QuestionAnswer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Integer> {

    @Query("select qa from QuestionAnswer qa join qa.question q where q.id = :question_id")
    List<QuestionAnswer> findByQuestionId(@Param("question_id") int question_id, Pageable pageable);
}
