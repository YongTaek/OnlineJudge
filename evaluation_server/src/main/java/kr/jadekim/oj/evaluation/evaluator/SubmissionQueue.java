package kr.jadekim.oj.evaluation.evaluator;

import kr.jadekim.oj.evaluation.models.Submission;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by jdekim43 on 2016. 2. 27..
 */
public class SubmissionQueue {

    private static volatile SubmissionQueue instance;

    private volatile Queue<Submission> queue = new LinkedList<>();

    public static SubmissionQueue getInstance() {
        if (instance == null) {
            synchronized (SubmissionQueue.class) {
                if (instance == null) {
                    instance = new SubmissionQueue();
                }
            }
        }
        return instance;
    }

    public void add(Submission submission) {
        queue.add(submission);
    }

    public Submission next() {
        return queue.poll();
    }

    public int count() {
        return queue.size();
    }
}
