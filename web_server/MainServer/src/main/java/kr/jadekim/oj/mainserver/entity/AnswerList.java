package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;
import java.util.ArrayList;

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
    private ArrayList<Object> answers;

    @OneToOne
    @JoinColumn(name="team")
    private Team team;

    public void setAnswers(ArrayList<Object> answers) {
        this.answers = answers;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public ArrayList<Object> getAnswers() {

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


    public AnswerList(Team team)
    {
        this.team = team;
        this.answers = new ArrayList<>();
    }
}
