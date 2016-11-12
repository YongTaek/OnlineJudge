package kr.jadekim.oj.mainserver.repository;


import kr.jadekim.oj.mainserver.entity.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface ContestRepository extends JpaRepository<Contest, Integer> {

    @Query("select case when count(c) > 0 then true else false end from Contest c where c.id=:contest_id and (:id in (select d.id from c.deputies d) or :id in (select d.id from c.requestDeputy d) or :id = (select a.id from c.admin a))")
    boolean findUserOfDeputiesOrRequestDeputies(@Param("id")int user_id, @Param("contest_id")int contestId);
}
