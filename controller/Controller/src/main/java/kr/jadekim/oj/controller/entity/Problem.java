package kr.jadekim.oj.controller.entity;

import javax.persistence.*;
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

    private String name;
    private String content;
    private int type;
    private String testcase;

    @OneToMany
    @JoinColumn(name="submitUsers")
    private List<User> submitUsers;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTestcase() {
        return testcase;
    }

    public void setTestcase(String testcase) {
        this.testcase = testcase;
    }

    @OneToMany
    @JoinColumn(name="user_id")
    public List<User> getSubmitUsers() {
        return submitUsers;
    }

    public void setSubmitUsers(List<User> submitUsers) {
        this.submitUsers = submitUsers;
    }

}
