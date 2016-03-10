package kr.jadekim.oj.mainserver.repository;

import kr.jadekim.oj.mainserver.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
public interface RankingRepository extends JpaRepository<Ranking, Integer> {
}
