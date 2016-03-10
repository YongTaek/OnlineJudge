package kr.jadekim.oj.mainserver.repository;


import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.entity.Testcase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 26..
 */
public interface TestcaseRepository extends JpaRepository<Testcase, Integer> {
    List<Testcase> findByProblem(Problem problem);
}
