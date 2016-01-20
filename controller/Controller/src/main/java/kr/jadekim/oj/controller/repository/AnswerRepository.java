package kr.jadekim.oj.controller.repository;

import kr.jadekim.oj.controller.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface AnswerRepository extends JpaRepository<Answer,Integer>{
}
