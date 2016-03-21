package kr.jadekim.oj.evaluation.models;

import com.google.gson.annotations.SerializedName;

import java.io.File;

/**
 * Created by jdekim43 on 2016. 1. 21..
 */
public enum Language {

    @SerializedName("c")
    C("c") {
        @Override
        public String getCompileCommand(File file) {
            return "gcc-4.8 Main.c";
        }

        @Override
        public String getExtension() {
            return "c";
        }

        @Override
        public String[] getRunCommand() {
            return new String[] {"a.out"};
        }
    },

    @SerializedName("cpp")
    CPP("cpp") {
        @Override
        public String getCompileCommand(File file) {
            return "g++-4.8 Main.cpp";
        }

        @Override
        public String getExtension() {
            return "cpp";
        }

        @Override
        public String[] getRunCommand() {
            return new String[] {"a.out"};
        }
    },

    @SerializedName("java")
    JAVA("java") {
        @Override
        public String getCompileCommand(File file) {
            String path = file.getAbsolutePath();
            return "javac Main.java";
        }

        @Override
        public String getExtension() {
            return "java";
        }

        @Override
        public String[] getRunCommand() {
            return new String[] {"java", "Main"};
        }
    },

    @SerializedName("python2")
    PYTHON2("python2") {
        @Override
        public String getCompileCommand(File file) {
            return null;
        }

        @Override
        public String getExtension() {
            return "py";
        }

        @Override
        public String[] getRunCommand() {
            return new String[] {"python2", "Main.py"};
        }
    },

    @SerializedName("python3")
    PYTHON3("python3") {
        @Override
        public String getCompileCommand(File file) {
            return null;
        }

        @Override
        public String getExtension() {
            return "py";
        }

        @Override
        public String[] getRunCommand() {
            return new String[] {"python3", "Main.py"};
        }
    };

    @SerializedName("language")
    private String language;

    Language(String language) {
        this.language = language;
    }

    public abstract String getCompileCommand(File file);

    public abstract String[] getRunCommand();

    public abstract String getExtension();

    @Override
    public String toString() {
        return language;
    }
}
