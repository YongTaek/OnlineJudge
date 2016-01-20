package kr.jadekim.oj.controller.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_question_answer")
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="question")
    private Question question;

    @OneToOne
    @JoinColumn(name="board_id")
    private Board board;

    @Column(name="time")
    private Date time;

    @JoinColumn(name="user_id")
    private User author;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @ElementCollection
    @CollectionTable(name="attachFiles")
    @Column(name="attachFile")
    private List<String> attachFiles;

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
