package kr.jadekim.oj.mainserver.service;

import kr.jadekim.oj.mainserver.entity.ProblemSet;
import kr.jadekim.oj.mainserver.repository.ProblemSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;


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

    public List<ProblemSet> findAllProblemSets(Pageable pageable) {
        return problemSetRepository.findAll(pageable).getContent();
    }
    public int countAllProblem(int problemSetId,int userId){
        return problemSetRepository.countAllProblem(userId);
    }

    @Async
    public Future<ProblemSet> findOne(int id) {
        return new AsyncResult<>(problemSetRepository.getOne(id));
    }
}
