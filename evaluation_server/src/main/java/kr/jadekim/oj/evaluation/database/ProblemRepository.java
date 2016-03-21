package kr.jadekim.oj.evaluation.database;

import kr.jadekim.oj.evaluation.models.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by jdekim43 on 2016. 2. 16..
 */
public interface ProblemRepository extends JpaRepository<Problem, Integer> {

    Problem findById(int id);
}
