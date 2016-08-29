package kr.jadekim.oj.mainserver.repository;


import kr.jadekim.oj.mainserver.entity.Answer;
import kr.jadekim.oj.mainserver.entity.GradeResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Query("select count(a.id) from Answer a join a.result r join a.problem p where r.isSuccess=true and p.id like :prob_id")
    int countBySuccessAndProblemId(@Param("prob_id") int prob_id);

    @Query("select count(a.id) from Answer a join a.problem p where p.id=:prob_id")
    int countByProblemId(@Param("prob_id") int prob_id);

    @Query(value = "select b from Answer a join a.result b join a.problem p on p.id=:prob_id join a.submitter u on u.id=:user_id order by a.submitTime desc")
    GradeResult findIsSuccessTop1ByUserId(@Param("user_id") int user_id, @Param("prob_id") int prob_id);

    @Query("select count(u.id) from Answer a join a.submitter u join a.problem p on p.id=:prob_id group by u")
    int countUserByProblemId(@Param("prob_id")int prob_id);

    @Query("select a from Answer a join a.submitter u on u.id=:user_id")
    List<Answer> findAnswerByUserId(@Param("user_id")int user_id);

    @Query("select distinct a.id from Answer a join a.submitter u on u.id=:user_id join a.result r on r.isSuccess=true")
    List<Integer> findSuccessAnswerByUserId(@Param("user_id")int user_id);
}
