package kr.jadekim.oj.mainserver.repository;

import kr.jadekim.oj.mainserver.entity.GradeResult;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface GradeResultRepository extends JpaRepository<GradeResult, Integer> {
}
