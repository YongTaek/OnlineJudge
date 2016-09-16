package kr.jadekim.oj.mainserver.repository;

import kr.jadekim.oj.mainserver.entity.Group;
import kr.jadekim.oj.mainserver.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface TeamRepository extends JpaRepository<Team, Integer> {


    @Query("select t from Team t join t.contest c on c.id =:contest_id")
    List<Team> findTeamListByContestId(@Param("contest_id") int contest_id);

//    @Query("select t from User u join u.teamList t join t.users s where s.id = u.id")

    @Query("select t from Team t join t.users u where u.id=:user_id")
    List<Team> findTeamListByUserId(@Param("user_id") int user_id);

}
