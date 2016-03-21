package kr.jadekim.oj.evaluation;

import kr.jadekim.oj.evaluation.models.Language;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;

/**
 * Created by jdekim43 on 2016. 2. 29..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {EvaluationServerApplication.class})
@WebAppConfiguration
public class CompilerTest {

    private String testCodePath = "/Users/jdekim43/Documents/Project/OnlineJudge/evaluation_server/src/main/resources/test_code/";

    @Test
    public void c() {
        File file = new File(testCodePath+"Main.c");
        System.out.println(kr.jadekim.oj.evaluation.evaluator.Compiler.compile(file, Language.C));
    }

    @Test
    public void cpp() {
        File file = new File(testCodePath+"Main.cpp");
        System.out.println(kr.jadekim.oj.evaluation.evaluator.Compiler.compile(file, Language.CPP));
    }

    @Test
    public void java() {
        File file = new File(testCodePath+"Main.java");
        System.out.println(kr.jadekim.oj.evaluation.evaluator.Compiler.compile(file, Language.JAVA));
    }
}