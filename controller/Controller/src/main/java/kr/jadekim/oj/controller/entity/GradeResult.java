package kr.jadekim.oj.controller.entity;

import javax.persistence.*;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_gradeResult")
public class GradeResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="isSuccess")
    private boolean isSuccess;

    @Column(name="failType")
    private int failType;

    @Column(name="runningTime")
    private double runningTime;

    @Column(name="usageMemory")
    private int usageMemory;

    @OneToOne
    @JoinColumn(name="answer")
    private Answer answer;

    @Column(name="errorMsg")
    private String errorMsg;

    public int getId() {
        return id;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getFailType() {
        return failType;
    }

    public double getRunningTime() {
        return runningTime;
    }

    public int getUsageMemory() {
        return usageMemory;
    }

    public Answer getAnswer() {
        return answer;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public void setFailType(int failType) {
        this.failType = failType;
    }

    public void setRunningTime(double runningTime) {
        this.runningTime = runningTime;
    }

    public void setUsageMemory(int usageMemory) {
        this.usageMemory = usageMemory;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
