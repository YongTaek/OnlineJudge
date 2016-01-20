package kr.jadekim.oj.controller.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 20..
 */
@Entity
@Table(name="tbl_answerList")
public class AnswerList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany
    @JoinColumn(name="answerList")
    private List<Answer> answers;

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Answer> getAnswers() {

        return answers;
    }

    public Team getTeam() {
        return team;
    }

    public boolean addAnswer(Answer answer){
        if(answers ==null){
            answers = new ArrayList<>();
        }
        return answers.add(answer);
    }
    @OneToOne
    @JoinColumn(name="team")
    private Team team;
}
