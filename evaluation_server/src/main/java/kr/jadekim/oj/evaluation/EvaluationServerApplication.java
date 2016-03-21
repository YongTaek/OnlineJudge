package kr.jadekim.oj.evaluation;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class EvaluationServerApplication {

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].contains("--setting") && i != args.length - 1) {
                loadSetting(args[i + i]);
            }
        }
        SpringApplication.run(EvaluationServerApplication.class, args);
    }

    private static void loadSetting(String settingFilePath) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(settingFilePath))) {
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Setting.set(gson.fromJson(sb.toString(), Setting.class));
    }
}
