package kr.jadekim.oj.mainserver.repository;


import kr.jadekim.oj.mainserver.entity.Board;
import kr.jadekim.oj.mainserver.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
