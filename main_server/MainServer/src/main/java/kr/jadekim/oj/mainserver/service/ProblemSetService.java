package kr.jadekim.oj.mainserver.service;

import kr.jadekim.oj.mainserver.entity.ProblemSet;
import kr.jadekim.oj.mainserver.repository.ProblemSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;


/**
 * Created by ohyongtaek on 2016. 2. 24..
 */
@Service
public class ProblemSetService {

    @Autowired
    ProblemSetRepository problemSetRepository;

    public Iterable<ProblemSet> findAllProblemSet(Pageable pageable){
        return problemSetRepository.findAll(pageable);
    }

    public int countAllProblem(int problemSetId,int userId){
        return problemSetRepository.countAllProblem(problemSetId,userId);
    }
}
