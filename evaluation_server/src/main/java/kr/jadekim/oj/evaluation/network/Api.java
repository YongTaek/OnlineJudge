package kr.jadekim.oj.evaluation.network;

import com.squareup.okhttp.OkHttpClient;
import kr.jadekim.oj.evaluation.ApiConstants;
import kr.jadekim.oj.evaluation.Setting;
import kr.jadekim.oj.evaluation.models.GradeResult;
import kr.jadekim.oj.evaluation.models.Problem;
import kr.jadekim.oj.evaluation.models.SimpleResult;
import kr.jadekim.oj.evaluation.utils.GsonUtils;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;
import rx.Observable;

/**
 * Created by jdekim43 on 2016. 2. 5..
 */
public class Api {

    private static volatile Api instance;

    private Service service;

    private Api() {
        RestAdapter adapter = buildRestAdapter();
        service = adapter.create(Service.class);
    }

    public static Api getInstance() {
        if (instance == null) {
            synchronized (Api.class) {
                if (instance == null) {
                    instance = new Api();
                }
            }
        }
        return instance;
    }

    private RestAdapter buildRestAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint(ApiConstants.BASE_API_URL)
                .setErrorHandler(new ApiErrorHandler())
                .setClient(getHttpClient())
                .setConverter(new GsonConverter(GsonUtils.getGsonObject()))
                .setLogLevel((Setting.get().DEBUG) ?
                        RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .build();
    }

    private Client getHttpClient() {
        OkHttpClient httpClient = new OkHttpClient();
        //httpClient.setConnectTimeout(ApiConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        //httpClient.setReadTimeout(ApiConstants.HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS);
        return new OkClient(httpClient);
    }

    public Observable<Problem> getProblem(int problemId) {
        return service.getProblem(problemId);
    }

    public Observable<SimpleResult> endEvaluate(GradeResult result) {

        return service.endEvaluation(result.getId(),
                result.getSubmission().getId(),
                result.isSuccess(),
                result.getGrade(),
                "");
    }
}