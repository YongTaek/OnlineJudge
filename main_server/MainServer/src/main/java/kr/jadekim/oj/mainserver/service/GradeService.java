package kr.jadekim.oj.mainserver.service;


import kr.jadekim.oj.mainserver.repository.GradeResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * Created by ohyongtaek on 2016. 1. 26..
 */

@Service
public class GradeService {

    @Autowired
    private GradeResultRepository gradeResultRepository;

    @Async
    public Future<String> sendForGrade(int problem_id, String lang, String code,int answerId){
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, Object> map= new LinkedMultiValueMap<String,Object>();
        map.add("lang",lang);
        map.add("code",code);
        map.add("submitId",answerId);
        map.add("problemId",problem_id);
        String gradeResult = restTemplate.postForObject("http://localhost:8080/api/join",map,String.class);
        return new AsyncResult<>(gradeResult);
    }
}
