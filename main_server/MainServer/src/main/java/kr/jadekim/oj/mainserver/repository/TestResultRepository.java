package kr.jadekim.oj.mainserver.repository;

import kr.jadekim.oj.mainserver.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ohyongtaek on 2016. 1. 26..
 */
public interface TestResultRepository extends JpaRepository<TestResult, Integer> {
}
