package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;

/**
 * Created by ohyongtaek on 2016. 1. 26..
 */
@Entity
@Table(name = "tbl_testcase")
public class Testcase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "input")
    private String input;

    @Column(name = "output")
    private String output;

    @Column(name = "type")
    private int type;


    @ManyToOne
    @JoinColumn(name = "problemId")
    private Problem problem;

    public Testcase(){}

    public Testcase(Problem problem, String input, String output){
        this.problem = problem;
        this.input = input;
        this.output = output;
    }

    public int getId() {
        return id;
    }

    public String getInput() {

        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }




}
