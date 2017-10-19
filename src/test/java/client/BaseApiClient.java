package client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.testng.log4testng.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * An abstract base for all API clients within this package.
 */
public abstract class BaseApiClient {

    protected static final String MEDIA_TYPE_APPLICATION_JSON = "application/json";
    protected static final String MEDIA_TYPE_APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
    protected static final String EMPTY_STRING = "";
    protected static final String SPACE_DELIMITER = " ";
    protected static final String ERRORS_TAG = "errors";
    protected static final String DATA_TAG = "data";
    protected static final String NOTICES_TAG = "notices";
    protected static final String SUCCESS_TAG = "success";
    protected static final String OPEN_BRACKET = "[";
    protected static final String CLOSE_BRACKET = "]";
    protected static final String QUERY_DELIMITER = "&";
    protected static final String KEY_VALUE_DELIMITER = "=";
    protected static final String UTF_8_ENCODING = "UTF-8";
    protected static final String PARAM_LIMIT = "limit";
    protected static final String PARAM_OFFSET = "offset";
    protected static final int DEFAULT_LIMIT = 10;
    protected static final int DEFAULT_OFFSET = 0;

    private static final String OPEN_RESULT_TAG = "<result>";
    private static final String CLOSE_RESULT_TAG = "</result>";
    private static final int TIMEOUT_CONNECTION = 90; // timeout in seconds
    private static final int TIMEOUT_SOCKET = 90; // timeout in seconds

    // properties
    protected static String serverApiBaseUrl = "https://qaserverapi.inventureaccess.com";

    /**
     * The logger.
     */
    protected Logger logger = Logger.getLogger( getClass() );

    //protected static String serverAuthAPiBaseUrl = PropertyUtils.getServerAuthApiBaseUrl();

    /**
     * Http client and urls.
     */
    protected OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout( TIMEOUT_SOCKET, TimeUnit.SECONDS ).connectTimeout( TIMEOUT_CONNECTION, TimeUnit.SECONDS ).build();

    protected static ObjectMapper mapper = new ObjectMapper().
            configure( DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false ).
            enable( SerializationFeature.INDENT_OUTPUT );

    /**
     * Returns get {@link Request}.
     *
     * @param httpUrl the http url
     */
    protected Request getRequest(final HttpUrl httpUrl) {
        return getRequest( httpUrl, null );
    }

    /**
     * Returns get {@link Request}.
     *
     * @param httpUrl the http url
     * @param headers the headers
     */
    protected Request getRequest(final HttpUrl httpUrl, final Headers headers) {
        Request.Builder builder = new Request.Builder().url( httpUrl ).get();
        if (null != headers) {
            builder.headers( headers );
        }
        return builder.build();
    }

    /**
     * Builds form body.
     *
     * @param queryString the query string
     */
    protected RequestBody buildFormBody(final String queryString) {
        FormBody.Builder builder = new FormBody.Builder();
        String[] params = StringUtils.split( queryString, QUERY_DELIMITER );
        for (String param : params) {
            String[] keyValue = StringUtils.split( param, KEY_VALUE_DELIMITER );
            builder.addEncoded( keyValue[0], keyValue[1] );
        }
        return builder.build();
    }

    /**
     * Returns post {@link Request}.
     *
     * @param httpUrl     the http url
     * @param requestBody the request body
     */
    protected Request postRequest(final HttpUrl httpUrl, final RequestBody requestBody) {
        return postRequest( httpUrl, requestBody, null );
    }

    /**
     * Returns post {@link Request}.
     *
     * @param httpUrl     the http url
     * @param requestBody the request body
     * @param headers     the headers
     */
    protected Request postRequest(final HttpUrl httpUrl, final RequestBody requestBody, final Headers headers) {
        Request.Builder builder = new Request.Builder().url( httpUrl ).post( requestBody );
        if (null != headers) {
            builder.headers( headers );
        }
        return builder.build();
    }

    /**
     * Returns put {@link Request}.
     *
     * @param httpUrl     the http url
     * @param requestBody the request body
     */
    protected Request putRequest(final HttpUrl httpUrl, final RequestBody requestBody) {
        return putRequest( httpUrl, requestBody, null );
    }

    /**
     * Returns put {@link Request}.
     *
     * @param httpUrl     the http url
     * @param requestBody the request body
     * @param headers     the paymentHeaders
     */
    protected Request putRequest(final HttpUrl httpUrl, final RequestBody requestBody, final Headers headers) {
        Request.Builder builder = new Request.Builder().url( httpUrl ).put( requestBody );
        if (null != headers) {
            builder.headers( headers );
        }
        return builder.build();
    }

    /**
     * Returns head {@link Request}.
     *
     * @param httpUrl the http url
     */
    protected Request headRequest(final HttpUrl httpUrl) {
        return headRequest( httpUrl, null );
    }

    /**
     * Returns head {@link Request}.
     *
     * @param httpUrl the http url
     * @param headers the headers
     */
    protected Request headRequest(final HttpUrl httpUrl, final Headers headers) {
        Request.Builder builder = new Request.Builder().url( httpUrl ).head();
        if (null != headers) {
            builder.headers( headers );
        }
        return builder.build();
    }

    /**
     * Returns delete {@link Request}.
     *
     * @param httpUrl the http url
     */
    protected Request deleteRequest(final HttpUrl httpUrl) {
        return deleteRequest( httpUrl, null );
    }

    /**
     * Returns delete {@link Request}.
     *
     * @param httpUrl the http url
     * @param headers the headers
     */
    protected Request deleteRequest(final HttpUrl httpUrl, final Headers headers) {
        Request.Builder builder = new Request.Builder().url( httpUrl ).delete();
        if (null != headers) {
            builder.headers( headers );
        }
        return builder.build();
    }

    /**
     * Executes a {@link Request} and returns a {@link Response} object.
     *
     * @param request the request
     * @throws IOException
     */
    protected Response executeRequest(final Request request) throws IOException {
        return okHttpClient.newCall( request ).execute();
    }

    /**
     * Returns {@link JSONObject} from {@link Response}.
     *
     * @param response the response
     * @throws IOException
     * @throws ApiClientException
     */
    protected JSONObject extractJsonObject(final Response response) throws IOException, ApiClientException {
        JSONObject jsonObject = new JSONObject( extractJsonString( response ) );
        if (hasErrors( jsonObject )) {
            throw new ApiClientException( flattenErrors( jsonObject ) );
        }
        return jsonObject;
    }

    /**
     * Returns true if json object has errors, otherwise false.
     *
     * @param jsonObject the json object
     */
    protected boolean hasErrors(final JSONObject jsonObject) {
        try {
            return jsonObject.getJSONArray( ERRORS_TAG ).length() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns a string of errors.
     *
     * @param jsonObject the json object.
     */
    protected String flattenErrors(final JSONObject jsonObject) {
        return jsonObject.getJSONArray( ERRORS_TAG ).toString();
    }

    /**
     * Returns json {@link String} from {@link Response}.
     *
     * @param response the response
     * @throws IOException
     */
    protected String extractJsonString(final Response response) throws IOException {
        String jsonString = StringUtils.replace( response.body().string(), OPEN_RESULT_TAG, EMPTY_STRING );
        response.body().close();
        return StringUtils.replace( jsonString, CLOSE_RESULT_TAG, EMPTY_STRING );
    }

    /**
     * Returns data enclosed with form tags.
     *
     * @param data the data
     */
    protected String toFormData(final String data) {
        return toBracketEnclosed( DATA_TAG, data );
    }

    /**
     * Returns data enclosed with brackets.
     *
     * @param prefix the prefix
     * @param data   the data
     */
    protected String toBracketEnclosed(final String prefix, final String data) {
        return prefix + OPEN_BRACKET + data + CLOSE_BRACKET;
    }
}
