package kr.jadekim.oj.controller.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_ranking")
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="type")
    private int type;

    @OneToMany
    @JoinColumn(name="user_id")
    private List<User> topUsers;

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public List<User> getTopUsers() {
        return topUsers;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTopUsers(List<User> topUsers) {
        this.topUsers = topUsers;
    }


}
