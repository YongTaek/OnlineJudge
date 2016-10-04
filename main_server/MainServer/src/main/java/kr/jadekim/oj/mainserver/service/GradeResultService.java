package kr.jadekim.oj.mainserver.service;

import kr.jadekim.oj.mainserver.entity.GradeResult;
import kr.jadekim.oj.mainserver.repository.GradeResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ohyongtaek on 2016. 10. 4..
 */
@Service
public class GradeResultService {

    @Autowired
    GradeResultRepository gradeResultRepository;

    public boolean save(GradeResult gradeResult) {
        if(gradeResult != null) {
            gradeResultRepository.save(gradeResult);
            return true;
        }else {
            return false;
        }
    }
}
