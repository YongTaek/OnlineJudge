package kr.jadekim.oj.controller.entity;

import javax.persistence.*;

/**
 * Created by ohyongtaek on 2016. 1. 26..
 */
@Entity
@Table(name = "tbl_testcase")
public class Testcase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name = "input")
    private String input;

    @Column(name = "output")
    private String output;

    @ManyToOne
    @JoinColumn(name = "problemId")
    private Problem problem;
}
