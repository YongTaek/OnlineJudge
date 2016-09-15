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

    @OneToMany
    @JoinColumn(name = "users")
    private List<User> users;

    @OneToOne
    @JoinColumn(name = "contests")
    private Contest contest;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "admin")
    private User admin;

    @OneToMany
    @Column(name = "result")
    private List<GradeResult> results;

    public Team() {
    }

    public Team(User admin, String name) {
        this.name = name;
        this.admin = admin;
        users = new ArrayList<>();
        contest = new Contest();
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

    public void setContests(Contest contest) {
        this.contest = contest;
    }
}
