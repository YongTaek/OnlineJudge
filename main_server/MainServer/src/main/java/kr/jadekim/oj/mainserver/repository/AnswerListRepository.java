package kr.jadekim.oj.mainserver.repository;


import kr.jadekim.oj.mainserver.entity.Answer;
import kr.jadekim.oj.mainserver.entity.AnswerList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 20..
 */
public interface AnswerListRepository extends JpaRepository<AnswerList, Integer> {

    @Query("select al from AnswerList a join a.answer al join a.team t on t.id=:team_id")
    List<Answer> findAnswerListByTeamId(@Param("team_id") int team_id);

}
