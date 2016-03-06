package kr.jadekim.oj.mainserver.repository;

import kr.jadekim.oj.mainserver.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
