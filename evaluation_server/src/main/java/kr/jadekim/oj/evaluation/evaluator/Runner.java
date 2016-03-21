package kr.jadekim.oj.evaluation.evaluator;

import kr.jadekim.oj.evaluation.utils.Logger;
import kr.jadekim.oj.evaluation.Setting;
import rx.Observable;
import rx.Scheduler;
import rx.observables.StringObservable;
import rx.schedulers.Schedulers;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jdekim43 on 2016. 2. 13..
 */
public class Runner {

    private ProcessBuilder processBuilder;
    private Process process;

    private StringBuilder result = new StringBuilder();
    private StringBuilder errorResult = new StringBuilder();

    public Runner(String path, String[] command, int limitMemory) {
        //TODO: check installed docker
        List<String> finallyCommand = new ArrayList<>();
        String[] runCommand = {"docker", "run", "-i", "-a", "STDOUT", "-a", "STDERR", "-m", limitMemory+"k",
                "-v", path+":/root/oj", Setting.get().DOCKER_CONTAINER};
        Collections.addAll(finallyCommand, runCommand);
        Collections.addAll(finallyCommand, command);
        processBuilder = new ProcessBuilder(finallyCommand);
    }

    public boolean run(int limitTime) throws IOException, InterruptedException {
        return run(limitTime, null);
    }

    public boolean run(int limitTime, String input) throws IOException, InterruptedException {
        process = processBuilder.start();

        StringObservable.decode(StringObservable.from(process.getInputStream()), Setting.get().CHAR_SET)
                .subscribeOn(Schedulers.newThread())
                .subscribe(result::append);
        StringObservable.decode(StringObservable.from(process.getErrorStream()), Setting.get().CHAR_SET)
                .subscribeOn(Schedulers.newThread())
                .subscribe(errorResult::append);

        if (input != null && !"".equals(input)) {
            write(input);
        }

        return process.waitFor(limitTime, TimeUnit.SECONDS);
    }

    public String getResult() {
        return result.toString();
    }

    public String getError() {
        return errorResult.toString();
    }

    private void write(String input) throws IOException {
        PrintWriter outputStream = new PrintWriter(process.getOutputStream());
        outputStream.write(input);
        outputStream.flush();
        outputStream.close();
    }

    public int getExitValue() {
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return process.exitValue();
    }
}