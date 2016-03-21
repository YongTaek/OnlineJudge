package kr.jadekim.oj.evaluation.network;

import kr.jadekim.oj.evaluation.models.ErrorResponse;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;

/**
 * Created by jdekim43 on 2016. 2. 5..
 */
public class ApiErrorHandler implements ErrorHandler {

    public static final String TAG = ApiErrorHandler.class.getSimpleName();

    public static final int BAD_REQUEST_ERROR = 400;
    public static final int SESSION_EXPIRED_ERROR = 401;
    public static final int FORBIDDEN_ERROR = 403;
    public static final int NOT_FOUND_ERROR = 404;
    public static final int PRECONDITION_FAILED_ERROR = 412;
    public static final int METHOD_NOT_ALLOWED_ERROR = 405;
    public static final int INTERNAL_SERVER_ERROR = 500;

    public ApiErrorHandler() {

    }

    @Override
    public Throwable handleError(RetrofitError cause) {
        switch (cause.getKind()) {
            case HTTP:
                handleHttpError(cause);
                break;
            case NETWORK:
                handleNetworkError(cause);
                break;
            case CONVERSION:
                handleConversionError(cause);
                break;
            case UNEXPECTED:
                handleUnexpectedError(cause);
                break;
            default:
                throw new AssertionError("Unknown error kind: " + cause.getKind());
        }

        return cause;
    }

    protected void handleNetworkError(RetrofitError cause) {
        // TODO: handle network error
        // Do nothing...
    }

    protected void handleConversionError(RetrofitError cause) {
        // TODO: handle conversion error
        // Do nothing...
    }

    protected void handleUnexpectedError(RetrofitError cause) {
        // TODO: handle unexpected error
        // Do nothing...
    }

    protected void handleHttpError(RetrofitError cause) {
        Response response = cause.getResponse();
        int statusCode = response.getStatus();

        if (statusCode >= BAD_REQUEST_ERROR) {
            String errorMessage = "";
            try {
                errorMessage = ((ErrorResponse) cause.getBodyAs(ErrorResponse.class)).detail;
            } catch (Exception e) {
                // Do nothings
            }
            switch (statusCode) {
                case BAD_REQUEST_ERROR:
                case NOT_FOUND_ERROR:
                case PRECONDITION_FAILED_ERROR:
                case METHOD_NOT_ALLOWED_ERROR:
                    break;
                case SESSION_EXPIRED_ERROR:
                case FORBIDDEN_ERROR:
                    break;
                case INTERNAL_SERVER_ERROR:
                    break;
            }
        }
    }
}