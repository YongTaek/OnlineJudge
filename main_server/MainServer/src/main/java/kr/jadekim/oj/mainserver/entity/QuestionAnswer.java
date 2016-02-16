package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;

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
    @JoinColumn(name="post_id")
    private Post post;

    public QuestionAnswer(){}

    public QuestionAnswer(Question question, Post post){
        this.question =question;
        this.post =post;
    }
    public int getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
