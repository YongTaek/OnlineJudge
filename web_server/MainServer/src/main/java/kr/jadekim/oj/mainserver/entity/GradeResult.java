package kr.jadekim.oj.mainserver.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ohyongtaek on 2016. 1. 19..
 */
@Entity
@Table(name="tbl_gradeResult")
public class GradeResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="isSuccess")
    private boolean isSuccess;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="testResult_id")
    List<TestResult> testResults;

    @Column(name="grade")
    private double grade;

    public GradeResult(){
        testResults = new ArrayList<>();
    }




    public void setGrade() {
        int count = testResults.size();
        int success = 0;
        for(int i=0;i<count;i++){
            if(testResults.get(i).getresult() == 0){
                success++;
            }
        }
        this.grade = (success/count)*100;
    }

    public double getGrade() {

        return grade;
    }




    public int getId() {
        return id;
    }

    public boolean isSuccess() {
        return isSuccess;
    }



    public void setSuccess(boolean success) {
        isSuccess = success;
    }



}
