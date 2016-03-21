package kr.jadekim.oj.evaluation;

import kr.jadekim.oj.evaluation.evaluator.Runner;
import kr.jadekim.oj.evaluation.models.Language;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

/**
 * Created by jdekim43 on 2016. 2. 29..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {EvaluationServerApplication.class})
@WebAppConfiguration
public class RunnerTest {

    private String testCodePath = "/Users/jdekim43/Documents/Project/OnlineJudge/evaluation_server/src/main/resources/test_code/";

    @Test
    public void c() {
        new CompilerTest().c();
        Runner runner = new Runner(testCodePath, Language.C.getRunCommand(), 10240);
        try {
            System.out.println("run : "+runner.run(1, "test input"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("result : "+runner.getResult());
        System.out.println("error : "+runner.getError());
        System.out.println("exit value : "+runner.getExitValue());
    }

    @Test
    public void cpp() {
        new CompilerTest().cpp();
        Runner runner = new Runner(testCodePath, Language.CPP.getRunCommand(), 10240);
        try {
            System.out.println("run : "+runner.run(1, "test input"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("result : "+runner.getResult());
        System.out.println("error : "+runner.getError());
        System.out.println("exit value : "+runner.getExitValue());
    }

    @Test
    public void java() {
        new CompilerTest().java();
        Runner runner = new Runner(testCodePath, Language.JAVA.getRunCommand(), 10240);
        try {
            System.out.println("run : "+runner.run(1, "test input"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("result : "+runner.getResult());
        System.out.println("error : "+runner.getError());
        System.out.println("exit value : "+runner.getExitValue());
    }
}
