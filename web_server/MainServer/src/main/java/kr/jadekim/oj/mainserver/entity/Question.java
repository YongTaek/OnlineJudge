package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_question")
public class Question{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name="post")
    private Post post;

    @ManyToOne
    @JoinColumn(name="problem")
    private Problem problem;

    @OneToMany(mappedBy = "question")
    private List<QuestionAnswer> answers;

    public int getId() {
        return id;
    }

    public Problem getProblem() {
        return problem;
    }

    public List<QuestionAnswer> getAnswers() {
        return answers;
    }


    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public void setAnswers(List<QuestionAnswer> answers) {
        this.answers = answers;
    }

    public Question(){}

    public Question(Post post,Problem problem){
        this.post = post;
        this.problem = problem;
        this.answers = new ArrayList<>();
    }
}
