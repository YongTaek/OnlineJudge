package kr.jadekim.oj.controller.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="login_id")
    private String login_id;
    @Column(name="login_pw")
    private String login_pw;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="rate")
    private double rate;

    @OneToMany
    @JoinColumn(name="problems")
    private List<Problem> problems;


    @OneToMany
    @JoinColumn(name="team_id")
    private List<Team> teamList;

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToOne
    @JoinColumn(name="group_id")
    private Group group;

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public String getEmail() {
        return email;
    }

    public double getRate() {
        return rate;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public int getId() {
        return id;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }

    public String getLogin_pw() {
        return login_pw;
    }

    public void setLogin_pw(String login_pw) {
        this.login_pw = login_pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public User(String login_id) {
        this.login_id = login_id;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
