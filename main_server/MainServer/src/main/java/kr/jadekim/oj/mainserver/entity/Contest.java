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
    @JoinColumn(name = "winner")
    private User winner;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subwinner")
    private User subwinner;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "problemSet")
    private ProblemSet problemSet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin")
    private User admin;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "deputy")
    private List<User> deputies;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "examiners")
    private List<User> examiners;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "sponsors")
    private List<User> sponsors;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "teams")
    private List<Team> teams;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "requestDeputy")
    private List<User> requestDeputy;

    @Column(name = "name")
    private String name;

    @ElementCollection
    @MapKey(name = "team")
    private Map<Team, AnswerList> solvedProblem;



    public Contest() {
        this.teams = new ArrayList<>();
        this.solvedProblem = new HashMap<>();
        this.teams = new ArrayList<>();
        this.deputies = new ArrayList<>();
        this.examiners = new ArrayList<>();
        this.sponsors = new ArrayList<>();
        this.requestDeputy = new ArrayList<>();
    }

    public Contest(Date startTime, Date endTime, String name) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.name = name;
        solvedProblem = new HashMap<>();
        this.teams = new ArrayList<>();
        this.examiners = new ArrayList<>();
        this.deputies = new ArrayList<>();
        this.sponsors = new ArrayList<>();
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

    public User getAdmin() {
        return admin;
    }

    public List<User>  getDeputies() {
        return deputies;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public List<User> getExaminers() {
        return examiners;
    }

    public List<User> getSponsors() {
        return sponsors;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public int getId(){ return id;}

    public User getWinner(){
        return winner;
    }
    public User getSubwinner(){
        return subwinner;
    }

    public List<User> getRequestDeputy() {return requestDeputy;}

}
