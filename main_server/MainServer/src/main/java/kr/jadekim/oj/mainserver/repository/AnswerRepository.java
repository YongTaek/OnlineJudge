package kr.jadekim.oj.mainserver.repository;


import kr.jadekim.oj.mainserver.entity.Answer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface AnswerRepository extends JpaRepository<Answer,Integer>{

    @Query("select count(a.id) from Answer a join a.result r join a.problem p where r.isSuccess=true and p.id like :prob_id")
    int countBySuccessAndProblemId(@Param("prob_id") int prob_id);

    @Query("select count(a.id) from Answer a join a.problem p where p.id=:prob_id")
    int countByProblemId(@Param("prob_id") int prob_id);

    @Query(value = "select b.isSuccess from Answer a join a.result b join a.problem p join a.submitter u where u.id=:user_id and p.id=:prob_id order by a.submitTime desc")
    boolean findIsSuccessTop1ByUserId(@Param("user_id") int user_id, @Param("prob_id") int prob_id);

}
