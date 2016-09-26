package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name = "tbl_team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToMany
    @JoinColumn(name = "users")
    private List<User> users;

    @OneToOne
    @JoinColumn(name = "contest")
    private Contest contest;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "admin")
    private User admin;

    @OneToMany
    @Column(name = "result")
    private List<GradeResult> results;

    @OneToMany
    @JoinColumn(name = "request_users",unique = true)
    private List<User> requestUsers; //try catch 하시고 에러 잡으세요


    public Team() {
    }

    public Team(User admin, String name) {
        this.name = name;
        this.admin = admin;
        users = new ArrayList<>();
        contest = new Contest();
        requestUsers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }

    public Contest getContest() {
        return contest;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public List<User> getRequestUsers() {
        return this.requestUsers;
    }

    public void setRequestUsers(List<User> requestUsers) {
        this.requestUsers = requestUsers;
    }
}
