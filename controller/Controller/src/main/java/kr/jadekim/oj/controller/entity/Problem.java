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

    @Column(name = "name")
    private String name;
    @Column(name = "content")
    private String content;
    @Column(name = "type")
    private int type;

    @OneToMany
    @JoinColumn(name = "testcase")
    private List<Testcase> testcases;

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

    @OneToMany
    @JoinColumn(name="user_id")
    public List<User> getSubmitUsers() {
        return submitUsers;
    }

    public void setSubmitUsers(List<User> submitUsers) {
        this.submitUsers = submitUsers;
    }

}
