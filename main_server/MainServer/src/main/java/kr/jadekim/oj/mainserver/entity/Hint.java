package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_hint")
public class Hint {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "problem")
    private Problem problem;

    @Column(name = "content")
    private String content;

    @OneToOne
    @JoinColumn(name = "author")
    private User author;

    @Column(name = "time")
    private Date time;

    @Column(name = "likeCount")
    private int likeCount;

    @Column(name = "badCount")
    private int badCount;

    public Hint() {
    }

    public Hint(Problem problem, String content, User author, Date time) {
        this.problem = problem;
        this.content = content;
        this.author = author;
        this.time = time;
        likeCount = 0;
        badCount = 0;
    }

    public int getId() {
        return id;
    }

    public Problem getProblem() {
        return problem;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public Date getTime() {
        return time;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getBadCount() {
        return badCount;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setBadCount(int badCount) {
        this.badCount = badCount;
    }
}
