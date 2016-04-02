package kr.jadekim.oj.mainserver.repository;


import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.entity.Testcase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 26..
 */
public interface TestcaseRepository extends JpaRepository<Testcase, Integer> {
    List<Testcase> findByProblem(Problem problem);

    @Query("select t from Testcase t join t.problem p on p.id=:prob_id where t.canVisible=true")
    Testcase findVisibleTestcaseByProbId(@Param("prob_id")int prob_id);
}
