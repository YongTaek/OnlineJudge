package kr.jadekim.oj.mainserver.repository;

import kr.jadekim.oj.mainserver.entity.Group;
import kr.jadekim.oj.mainserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface GroupRepository extends JpaRepository<Group, Integer> {


    @Query("select g from Group g join g.users u on u.id=:user_id")
    Group findGroupByUserId(@Param("user_id") int user_id);

}
