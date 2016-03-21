package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name = "tbl_problemSet")
public class ProblemSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "canModify")
    private boolean canModify;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "clearUsers")
    private List<User> clearUsers;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "problemSet")

    private List<Problem> problemList;

    public ProblemSet() {
    }

    public ProblemSet(User author, String name){

        this.name = name;
        this.clearUsers = new ArrayList<>();
        this.problemList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public List<User> getCelarUsers() {
        return clearUsers;
    }

    public List<Problem> getProblemList() {
        return problemList;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCelarUsers(List<User> celarUsers) {
        this.clearUsers = celarUsers;
    }

    public void setProblemList(List<Problem> problemList) {
        this.problemList = problemList;
    }
}
