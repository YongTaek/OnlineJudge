package kr.jadekim.oj.mainserver.repository;


import kr.jadekim.oj.mainserver.entity.Problem;
import kr.jadekim.oj.mainserver.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface ProblemRepository extends JpaRepository<Problem,Integer> {

    @Query("select count(p) from Problem p")
    int countAll();



    @Query("select p from Problem p join p.submitUsers u group by p order by count(u.id) desc")
    List<Problem> findOrderBySubmitUsers();

    List<Problem> findById(int problem_id);

    @Query("select p from Problem p where p.name like concat('%',:problem_name,'%')")
    List<Problem> findByName(@Param("problem_name") String problem_name);

}
