package kr.jadekim.oj.evaluation.evaluator;

import com.sun.istack.internal.Nullable;
import kr.jadekim.oj.evaluation.models.Submission;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Created by jdekim43 on 2016. 2. 27..
 */
public class SubmissionQueue implements Iterable<Submission> {

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

    @Nullable
    public Submission next() {
        return queue.poll();
    }

    public int count() {
        return queue.size();
    }

    @Override
    public Iterator<Submission> iterator() {
        return new Iterator<Submission>() {

            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            @Nullable
            public Submission next() {
                return queue.poll();
            }
        };
    }

    @Override
    public void forEach(Consumer<? super Submission> action) {
        while(!queue.isEmpty()) {
            action.accept(queue.poll());
        }
    }
}