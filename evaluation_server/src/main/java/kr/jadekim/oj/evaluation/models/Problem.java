package kr.jadekim.oj.evaluation.models;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jdekim43 on 2016. 2. 5..
 */
@Entity
@Table(name="tbl_problem")
public class Problem {

    @Id
    @SerializedName("id")
    private int id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="testcase")
    @SerializedName("testCases")
    private List<TestCase> testCases;

    @Column(name = "limit_time")
    @SerializedName("limit_time")
    private int limitTime;

    @Column(name = "limit_memory")
    @SerializedName("limit_memory")
    private int limitMemory;

    public int getId() {
        return id;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public int getLimitTime() {
        return limitTime;
    }

    public int getLimitMemory() {
        return limitMemory;
    }
}
