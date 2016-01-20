package kr.jadekim.oj.controller.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name="problem")
    private Problem problem;

    @Column(name="code")
    private String code;

    @OneToOne
    @JoinColumn(name="result")
    private GradeResult result;

    @Column(name="submitTime")
    private Date submitTime;

    @OneToOne
    @JoinColumn(name="submitter")
    private User submitter;

    @ManyToOne
    @JoinColumn(name="contest_id")
    private Contest contest;

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setResult(GradeResult result) {
        this.result = result;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public void setSubmitter(User submitter) {
        this.submitter = submitter;
    }

    public int getId() {

        return id;
    }

    public Problem getProblem() {
        return problem;
    }

    public String getCode() {
        return code;
    }

    public GradeResult getResult() {
        return result;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public User getSubmitter() {
        return submitter;
    }
}
