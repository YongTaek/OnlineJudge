package kr.jadekim.oj.controller.repository;

import kr.jadekim.oj.controller.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface BoardRepository extends JpaRepository<Board,Integer> {
}
