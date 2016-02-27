package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_contest")
public class Contest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="startTime")
    private Date startTime;

    @Column(name="endTime")
    private Date endTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="problemSet")
    private ProblemSet problemSet;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="manageTeam")
    private Team manageTeam;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="teams")
    private List<Team> teams;

    @Column(name = "name")
    private String name;


    @ElementCollection
    @MapKey(name="team")
    private Map<Team,AnswerList> solvedProblem;

    public Contest(){
        this.teams = new ArrayList<>();
        this.solvedProblem = new HashMap<>();
    }

    public Contest(Team manageTeam, Date startTime,Date endTime, String name,ProblemSet problemSet){
        this.manageTeam = manageTeam;
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        solvedProblem = new HashMap<>();
        teams = new ArrayList<>();
        this.problemSet = problemSet;
    }

    public AnswerList getAnswerList(Team team){
        if(!solvedProblem.containsKey(team)){
            return null;
        }
        return solvedProblem.get(team);
    }

    public boolean addSolvedProblem(Team team,Answer answer){
        if(!solvedProblem.containsKey(team)){
            AnswerList answers = new AnswerList(team);
            answers.setAnswers(new ArrayList<Answer>());
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

    public void setManageTeam(Team manageTeam) {
        this.manageTeam = manageTeam;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSolvedProblem(Map<Team, AnswerList> solvedProblem) {
        this.solvedProblem = solvedProblem;
    }

    public Team getManageTeam() {

        return manageTeam;
    }

    public String getName() {
        return name;
    }

    public Map<Team, AnswerList> getSolvedProblem() {
        return solvedProblem;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }


}
