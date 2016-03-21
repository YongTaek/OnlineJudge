package kr.jadekim.oj.evaluation;

import kr.jadekim.oj.evaluation.evaluator.Checker;
import kr.jadekim.oj.evaluation.models.Language;
import kr.jadekim.oj.evaluation.models.TestCase;
import kr.jadekim.oj.evaluation.models.TestResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by jdekim43 on 2016. 2. 29..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {EvaluationServerApplication.class})
@WebAppConfiguration
public class CheckerTest {

    private String testCodePath = "/Users/jdekim43/Documents/Project/OnlineJudge/evaluation_server/src/main/resources/test_code/";

    @Test
    public void c() {
        new CompilerTest().c();
        TestCase testCase = new TestCase(1, "text", "input : text\n");
        Checker checker = new Checker(testCodePath, Language.C.getRunCommand(), testCase, 2, 10240);
        TestResult result = checker.run();
        System.out.println(result.getResultType().toString());
        System.out.println(result.getErrorMessage());
    }

    @Test
    public void cpp() {
        new CompilerTest().cpp();
        TestCase testCase = new TestCase(1, "text", "input : text");
        Checker checker = new Checker(testCodePath, Language.CPP.getRunCommand(), testCase, 2, 10240);
        TestResult result = checker.run();
        System.out.println(result.getResultType().toString());
        System.out.println(result.getErrorMessage());
    }

    @Test
    public void java() {
        new CompilerTest().java();
        TestCase testCase = new TestCase(1, "text", "input : text\n");
        Checker checker = new Checker(testCodePath, Language.JAVA.getRunCommand(), testCase, 2, 10240);
        TestResult result = checker.run();
        System.out.println(result.getResultType().toString());
        System.out.println(result.getErrorMessage());
    }
}
