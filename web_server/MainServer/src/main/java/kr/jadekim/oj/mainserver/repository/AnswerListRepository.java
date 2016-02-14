package kr.jadekim.oj.mainserver.repository;


import kr.jadekim.oj.mainserver.entity.AnswerList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ohyongtaek on 2016. 1. 20..
 */
public interface AnswerListRepository extends JpaRepository<AnswerList,Integer> {
}
