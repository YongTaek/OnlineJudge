package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_answer")
public class Answer implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="problem")

    private Problem problem;

    @Column(name = "code")
    private String code;

    @OneToOne
    @JoinColumn(name = "result")
    private GradeResult result;

    @Column(name = "submitTime")
    private Date submitTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User submitter;

    @ManyToOne
    @JoinColumn(name = "contest_id")
    private Contest contest;

    public Contest getContest() {
        return contest;
    }

    public Answer() {
    }

    public Answer(User submitter, String code, Date submitTime, Problem problem) {
        this.submitter = submitter;
        this.submitTime = submitTime;
        this.code = code;
        this.problem = problem;
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
