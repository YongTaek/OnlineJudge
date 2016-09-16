package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;
import java.util.*;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_contest")
public class Contest  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "startTime")
    private Date startTime;

    @Column(name = "endTime")
    private Date endTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "problemSet")
    private ProblemSet problemSet;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "admins")
    private List<User> adminUsers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "examiners")
    private List<User> examiners;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "sponsors")
    private List<User> sponsors;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team")
    private Team team;

    @Column(name = "name")
    private String name;

    @ElementCollection
    @MapKey(name = "team")
    private Map<Team, AnswerList> solvedProblem;



    public Contest() {
        this.team = new Team();
        this.solvedProblem = new HashMap<>();
    }

    public Contest(Date startTime, Date endTime, String name,List<User> adminUsers,List<User> examiners,List<User> sponsors) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        solvedProblem = new HashMap<>();
        team = new Team();
        this.adminUsers = adminUsers;
        this.examiners = examiners;
        this.sponsors = sponsors;
    }

    public AnswerList getAnswerList(Team team) {
        if (!solvedProblem.containsKey(team)) {
            return null;
        }
        return solvedProblem.get(team);
    }

    public boolean addSolvedProblem(Team team, Answer answer) {
        if (!solvedProblem.containsKey(team)) {
            AnswerList answers = new AnswerList(team);
            answers.setAnswers(new ArrayList<Answer>());
            answers.setTeam(team);
            solvedProblem.put(team, answers);
        }
        AnswerList answerList = solvedProblem.get(team);
        answerList.addAnswer(answer);
        return true;
    }

    public List<Answer> getSolvedProblem(Team team) {
        if (!solvedProblem.containsKey(team)) {
            return null;
        } else {
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


    public Team getTeam() {
        return team;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setSolvedProblem(Map<Team, AnswerList> solvedProblem) {
        this.solvedProblem = solvedProblem;
    }


    public String getName() {
        return name;
    }

    public Map<Team, AnswerList> getSolvedProblem() {
        return solvedProblem;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getId(){ return id;}

}
