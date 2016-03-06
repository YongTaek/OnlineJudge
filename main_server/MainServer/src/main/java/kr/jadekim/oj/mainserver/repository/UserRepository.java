package kr.jadekim.oj.mainserver.repository;

import kr.jadekim.oj.mainserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByloginId(String user_id);

}
