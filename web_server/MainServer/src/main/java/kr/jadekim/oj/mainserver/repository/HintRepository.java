package kr.jadekim.oj.mainserver.repository;

import kr.jadekim.oj.mainserver.entity.Hint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface HintRepository extends JpaRepository<Hint,Integer>{
}
