package kr.jadekim.oj.mainserver.repository;

import kr.jadekim.oj.mainserver.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByloginId(String user_id);

    @Query("select count(a) from User u join u.answers a on a.result.isSuccess=true")
    int countBySuccessCount();

    Optional<User> getByLoginId(String user_id);


    @Query("select u from User u order by success_count")
    List<User> findUserOrderBySuccessCount(Pageable pageable);

}
