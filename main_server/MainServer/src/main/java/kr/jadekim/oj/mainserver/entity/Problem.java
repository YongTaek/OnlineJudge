package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_problem")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "content")
    private String content;

    @Column(name="limitTime")
    private int limitTime;

    @Column(name="limitMemory")
    private int limitMemory;

    @OneToMany
    @JoinColumn(name = "testcase")
    private List<Testcase> testcases;

    @OneToMany
    @JoinColumn(name="submitUsers")
    private List<User> submitUsers;

    @OneToMany
    @JoinColumn(name="user_id")
    public List<User> getSubmitUsers() {
        return submitUsers;
    }

    public int getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(int limitTime) {
        this.limitTime = limitTime;
    }

    public int getLimitMemory() {
        return limitMemory;
    }

    public void setLimitMemory(int limitMemory) {
        this.limitMemory = limitMemory;
    }

    public List<Testcase> getTestcases() {
        return testcases;
    }

    public void setTestcases(List<Testcase> testcases) {
        this.testcases = testcases;
    }

    public Problem(){}

    public Problem(String name, String content, int type){
        this.name = name;
        this.content  = content;
        submitUsers = new ArrayList<>();
        this.testcases = new ArrayList<>();
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSubmitUsers(List<User> submitUsers) {
        this.submitUsers = submitUsers;
    }

}
