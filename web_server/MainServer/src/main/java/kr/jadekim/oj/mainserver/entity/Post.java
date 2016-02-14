package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(mappedBy = "post")
    private Question question;

    @OneToOne
    @JoinColumn(name="board")
    private Board board;

    @Column(name="time")
    private Date time;

    @JoinColumn(name="author")
    private User author;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @ElementCollection
    @CollectionTable(name="attachFiles")
    @Column(name="attachFile")
    private List<String> attachFiles;

    public Post(){}

    public Post(Board board,User user,Date time, String title,String content){
        this.board =board;
        this.author = user;
        this.title = title;
        this.content = content;
        this.time = time;
    }
    public int getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public Date getTime() {
        return time;
    }

    public User getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<String> getAttachFiles() {
        return attachFiles;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAttachFiles(List<String> attachFiles) {
        this.attachFiles = attachFiles;
    }


}
