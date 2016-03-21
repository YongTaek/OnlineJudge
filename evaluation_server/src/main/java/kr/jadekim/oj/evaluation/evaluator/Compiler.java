package kr.jadekim.oj.evaluation.evaluator;

import kr.jadekim.oj.evaluation.Setting;
import kr.jadekim.oj.evaluation.models.Language;
import kr.jadekim.oj.evaluation.utils.Logger;
import rx.observables.StringObservable;
import rx.schedulers.Schedulers;

import java.io.*;

/**
 * Created by jdekim43 on 2016. 2. 13..
 */
public class Compiler {

    public static void getCompilerVersion() {
        //TODO:
    }

    public static String compile(File file, Language language) {
        try {
//            Process process = Runtime.getRuntime().exec(language.getCompileCommand(file));
            String path = file.getAbsolutePath();
            Process process = Runtime.getRuntime().exec("docker run -i -a STDOUT -a STDERR -v "+
                    path.substring(0,path.lastIndexOf(File.separator))+":/root/oj "+ Setting.get().DOCKER_CONTAINER+" "+
                    language.getCompileCommand(file));
            StringBuilder result = new StringBuilder();
            StringBuilder errorResult = new StringBuilder();
            StringObservable.decode(StringObservable.from(process.getInputStream()), Setting.get().CHAR_SET)
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(result::append);
            StringObservable.decode(StringObservable.from(process.getErrorStream()), Setting.get().CHAR_SET)
                    .subscribeOn(Schedulers.newThread())
                    .subscribe(errorResult::append);
            process.waitFor();
            return "\n----result----\n"+result.toString()+"\n----error----\n"+errorResult.toString();
        } catch (IOException | InterruptedException e) {
            Logger.error(e);
        }
        return "compile run error";
    }
}
