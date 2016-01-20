package kr.jadekim.oj.controller.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_contest")
public class Contest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="startTime")
    private Date startTime;

    @Column(name="endTime")
    private Date endTime;

    @OneToOne
    @JoinColumn(name="problemSet")
    private ProblemSet problemSet;

    @Column(name="manageTeam")
    private String manageTeam;

    @OneToMany
    @JoinColumn(name="teams")
    private List<Team> teams;



    @ElementCollection
    @MapKey(name="team")
    private Map<Team,AnswerList> solvedProblem;

    public AnswerList getAnswerList(Team team){
        if(!solvedProblem.containsKey(team)){
            return null;
        }
        return solvedProblem.get(team);
    }

    public boolean addSolvedProblem(Team team,Answer answer){
        if(solvedProblem == null){
            solvedProblem = new HashMap<>();
        }
        if(!solvedProblem.containsKey(team)){
            AnswerList answers = new AnswerList();
            answers.setAnswers(new ArrayList<>());
            answers.setTeam(team);
            solvedProblem.put(team,answers);
        }
        AnswerList answerList = solvedProblem.get(team);
        answerList.addAnswer(answer);
        return true;
    }

    public List<Answer> getSolvedProblem(Team team){
        if(!solvedProblem.containsKey(team)){
            return null;
        }else{
            return solvedProblem.get(team).getAnswers();
        }
    }
    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public ProblemSet getProblemSet() {
        return problemSet;
    }

    public String getManageTeam() {
        return manageTeam;
    }

    public List<Team> getTeams() {
        return teams;
    }



    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setProblemSet(ProblemSet problemSet) {
        this.problemSet = problemSet;
    }

    public void setManageTeam(String manageTeam) {
        this.manageTeam = manageTeam;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }


}
