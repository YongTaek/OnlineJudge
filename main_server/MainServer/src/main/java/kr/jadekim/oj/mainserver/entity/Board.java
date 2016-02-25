package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_board")
public class Board implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="name")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public Board(){};
    public Board(String name){
        this.name =name;
    }
    public String getName() {
        return name;
    }
}
