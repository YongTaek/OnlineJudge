package kr.jadekim.oj.evaluation.evaluator;

import kr.jadekim.oj.evaluation.Setting;
import kr.jadekim.oj.evaluation.utils.Logger;
import kr.jadekim.oj.evaluation.models.*;
import rx.Observable;
import rx.Scheduler;
import rx.observables.BlockingObservable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by jdekim43 on 2016. 2. 16..
 */
public class Evaluator {

    private PublishSubject<Submission> subject = PublishSubject.create();

    public static Evaluator newInstance() {
        return new Evaluator();
    }

    public Observable<GradeResult> start() {
        Submission submission = SubmissionQueue.getInstance().next();
        if (submission == null) {
            subject.onCompleted();
        } else {
            subject.onNext(submission);
        }
        return subject.map(this::evaluate);
    }

    private GradeResult evaluate(Submission submission) {
        GradeResult gradeResult = new GradeResult(submission);

        String path = Setting.get().EVALUATION_TEMP_PATH+codeToUniqueString(submission.getCode())+"/";
        File file = saveToFile(path+"Main."+submission.getLanguage().getExtension(), submission.getCode());
        String compileResult = Compiler.compile(file, submission.getLanguage());

        if (compileResult != null && !"".equals(compileResult)) {
            gradeResult.setSuccess(false);
            gradeResult.setGrade(0);
            gradeResult.setMessage(compileResult);
            return gradeResult;
        }

        List<TestCase> testCases = submission.getProblem().getTestCases();
        List<TestResult> testResults = new ArrayList<TestResult>();

        testCases.forEach(testCase ->
                testResults.add(new Checker(path, submission.getLanguage().getRunCommand(), testCase,
                        submission.getProblem().getLimitTime(), submission.getProblem().getLimitMemory()).run())
        );

        int successCount = 0;
        for (TestResult testResult : testResults) {
            if (ResultType.SUCCESS.equals(testResult.getResultType())) {
                successCount++;
            }
        }
        gradeResult.setTests(testResults);
        gradeResult.setGrade((successCount/testCases.size())*100.0);
        gradeResult.setSuccess(successCount == testCases.size());
        return gradeResult;
    }

    private static String codeToUniqueString(String contentString) {
        String name = contentString + '\n'+new Date().toString()+'\n'+"this is sort : "+new Random().nextDouble();
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(name.getBytes());
            return new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return name;
    }

    private static File saveToFile(String path, String code) {
        File file = new File(path);
        try (PrintWriter writer = new PrintWriter(new java.io.FileWriter(file))) {
            writer.write(code);
            writer.flush();
        } catch (IOException e) {
            Logger.error(e);
        }
        return file;
    }
}