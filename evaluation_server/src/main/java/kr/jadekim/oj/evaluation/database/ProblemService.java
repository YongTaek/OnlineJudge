package kr.jadekim.oj.evaluation.database;

import kr.jadekim.oj.evaluation.Setting;
import kr.jadekim.oj.evaluation.controllers.EvaluationController;
import kr.jadekim.oj.evaluation.evaluator.Evaluator;
import kr.jadekim.oj.evaluation.evaluator.SubmissionQueue;
import kr.jadekim.oj.evaluation.models.*;
import kr.jadekim.oj.evaluation.network.Api;
import kr.jadekim.oj.evaluation.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ohyongtaek on 2016. 9. 4..
 */
@Service
public class ProblemService {

    @Autowired
    ProblemRepository problemRepository;


    public Problem findProblemById(int problemId) {
        return problemRepository.findById(problemId);
    }

    public SimpleResult startEvaluation(long id, String language, String code, int problemId) {
        SubmissionQueue.getInstance().add(createSubmission(id, Language.valueOf(language),code,problemRepository.findById(problemId)));
        setEvaluators();
        return new SimpleResult(true, Integer.toString(SubmissionQueue.getInstance().count()));
    }

    protected void endEvaluate(GradeResult result) {
        System.out.println("endEvaluate");
        Api.getInstance().endEvaluate(result);
    }

    protected Submission createSubmission(long id, Language language, String code, Problem problem) {
        Submission submission = new Submission(id, language, code);
        System.out.println("submission object create");
        submission.setProblem(problem);
        return submission;
    }

    private void setEvaluators() {
        for (int i = EvaluationController.evaluators.size(); i< Setting.get().EVALUATOR_COUNT; i++) {
            Evaluator evaluator = Evaluator.newInstance();
            EvaluationController.evaluators.add(evaluator);
            evaluator.start().subscribe(this::endEvaluate, Logger::error, () -> EvaluationController.evaluators.remove(evaluator));
        }
    }
}
