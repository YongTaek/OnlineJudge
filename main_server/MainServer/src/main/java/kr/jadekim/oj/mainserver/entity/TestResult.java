package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;

/**
 * Created by ohyongtaek on 2016. 1. 26..
 */
@Entity
@Table(name = "tbl_testResult")
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "result")
    private int result;

    @Column(name = "runningTime")
    private double runningTime;

    @Column(name = "usageMemory")
    private int usageMemory;

    @Column(name = "errorMsg")
    private String errorMsg;

    public int getresult() {
        return result;
    }

    public void setresult(int result) {
        this.result = result;
    }

    public double getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(double runningTime) {
        this.runningTime = runningTime;
    }

    public int getUsageMemory() {
        return usageMemory;
    }

    public void setUsageMemory(int usageMemory) {
        this.usageMemory = usageMemory;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
