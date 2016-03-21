package kr.jadekim.oj.evaluation.network;

import kr.jadekim.oj.evaluation.models.Problem;
import kr.jadekim.oj.evaluation.models.SimpleResult;
import kr.jadekim.oj.evaluation.models.TestCase;
import retrofit.http.*;
import rx.Observable;

import java.util.List;

/**
 * Created by jdekim43 on 2016. 2. 5..
 */
public interface Service {

    @GET("/v1/problem/{id}")
    Observable<Problem> getProblem(@Path("id") int problemId);

    @FormUrlEncoded
    @POST("/v1/evaluation/result")
    Observable<SimpleResult> endEvaluation(@Field("id") long id,
                                           @Field("submission_id") long submissionId,
                                           @Field("is_success") boolean isSuccess,
                                           @Field("grade") double grade,
                                           @Field("tests") String tests);
}
