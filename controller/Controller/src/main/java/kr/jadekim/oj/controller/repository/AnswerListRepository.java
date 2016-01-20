package kr.jadekim.oj.controller.repository;

import kr.jadekim.oj.controller.entity.AnswerList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ohyongtaek on 2016. 1. 20..
 */
public interface AnswerListRepository extends JpaRepository<AnswerList,Integer> {
}
