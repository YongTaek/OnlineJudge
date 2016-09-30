package kr.jadekim.oj.evaluation.models;

import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jdekim43 on 2016. 1. 21..
 */
@Entity
@Table(name="tbl_test_case")
public class TestCase {

    @Id
    @SerializedName("id")
    private int id;

    @Column(name = "type")
    @SerializedName("type")
    private int type;

    @Column(name = "input")
    @SerializedName("input")
    private String input;

    @Column(name = "output")
    @SerializedName("output")
    private String output;

    public TestCase(int id, String input, String output) {
        this.id = id;
        this.input = input;
        this.output = output;
    }

    public int getId() {
        return id;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }
}
