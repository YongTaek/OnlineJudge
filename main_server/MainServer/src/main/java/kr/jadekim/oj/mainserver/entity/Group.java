package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_group")
public class Group implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany
    @JoinColumn(name="users")
    private List<User> users;

    @ManyToMany
    @JoinColumn(name="problemSet")
    private List<ProblemSet> mustProblemSet;

    @Column(name="isPrivateJoin")
    private boolean isPrivateJoin;

    @OneToOne
    @JoinColumn(name="jjang")
    private User jjang;

    public Group(){}

    public Group(User jjang, boolean isPrivateJoin){
        this.jjang =jjang;
        this.users = new ArrayList<>();
        this.users.add(jjang);
        this.mustProblemSet = new ArrayList<>();
        this.isPrivateJoin = isPrivateJoin;
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
}
