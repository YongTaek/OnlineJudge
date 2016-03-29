package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany
    @JoinColumn(name = "users")
    private List<User> users;

    @OneToMany
    @JoinColumn(name = "waitUsers")
    private List<User> waitUsers;

    @ManyToMany
    @JoinColumn(name="problemSet")
    private List<ProblemSet> mustProblemSet;

    @Column(name = "isPrivateJoin")
    private boolean isPrivateJoin;

    @OneToOne
    @JoinColumn(name = "jjang")
    private User jjang;

    @Column(name = "name")
    private String name;

    public Group() {
    }

    public Group(User jjang, boolean isPrivateJoin,String name) {
        this.jjang = jjang;
        this.users = new ArrayList<>();
        this.users.add(jjang);
        this.name = name;
        this.mustProblemSet = new ArrayList<>();
        this.waitUsers = new ArrayList<>();
        this.isPrivateJoin = isPrivateJoin;
    }

    public List<User> getWaitUsers() {
        return waitUsers;
    }

    public void setWaitUsers(List<User> waitUsers) {
        this.waitUsers = waitUsers;
    }

    public int getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<ProblemSet> getMustProblemSet() {
        return mustProblemSet;
    }

    public boolean isPrivateJoin() {
        return isPrivateJoin;
    }

    public User getJjang() {
        return jjang;
    }

    public String getName(){ return name;}

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setMustProblemSet(List<ProblemSet> mustProblemSet) {
        this.mustProblemSet = mustProblemSet;
    }

    public void setPrivateJoin(boolean privateJoin) {
        isPrivateJoin = privateJoin;
    }

    public void setJjang(User jjang) {
        this.jjang = jjang;
    }

    public void setName(String name){this.name = name;}
}
