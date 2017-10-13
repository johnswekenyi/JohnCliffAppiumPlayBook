package client;

import client.ApiClientException;
import client.BaseApiClient;
import okhttp3.HttpUrl;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

/**
 * Retrieve confirmation code API client.
 */
public class ConfirmationCodeApiClient extends BaseApiClient {

    private static final String PATH_CRON_SEND_OUTGOING_MESSAGES = "/cron/sendOutgoingMessages";
    private static final String PATH_API_OUTGOING_MESSAGE_LOG = "/api/OutgoingMessageLog";
    private static final String PATH_API_OUTGOING_MESSAGE = "/api/OutgoingMessage";
    private static final String PARAM_TO = "to";
    private static final String PARAM_TEMPLATE = "template";
    private static final String CONFIRMATION_CODE_TEMPLATE = "110";
    private static final String ONE_TIME_PIN_TEMPLATE = "112";
    private static final String ID = "id";
    private static final String MESSAGE = "message";

    private HttpUrl sendOutgoingMessagesApiUrl = HttpUrl.parse(serverApiBaseUrl + PATH_CRON_SEND_OUTGOING_MESSAGES);
    private HttpUrl outgoingMessageLogApiUrl = HttpUrl.parse(serverApiBaseUrl + PATH_API_OUTGOING_MESSAGE_LOG);
    private HttpUrl outgoingMessageApiUrl = HttpUrl.parse(serverApiBaseUrl + PATH_API_OUTGOING_MESSAGE);

    /**
     * Returns confirmation code given a phone number.
     *
     * @param phoneNumber
     */
    public String retrieveConfirmationCode(final String phoneNumber) throws IOException, ApiClientException {
        // send outgoing messages
        executeRequest(getRequest(sendOutgoingMessagesApiUrl));

        // retrieve outgoing message log
        HttpUrl httpUrl = outgoingMessageApiUrl.newBuilder()
                .addQueryParameter(PARAM_TO, phoneNumber)
                .addQueryParameter(PARAM_TEMPLATE, CONFIRMATION_CODE_TEMPLATE)
                .build();
        Response response = executeRequest(getRequest(httpUrl));
        JSONArray jsonArray = extractJsonObject(response).getJSONArray(DATA_TAG);
        Iterator iterator = jsonArray.iterator();
        int id = -1;
        String message = null;
        while (iterator.hasNext()) {
            JSONObject item = new JSONObject(iterator.next().toString());
            if (id < item.getInt(ID)) {
                id = item.getInt(ID);
                message = item.getString(MESSAGE);
            }
        }
        return StringUtils.split(message, SPACE_DELIMITER)[0];
    }

    /**
     * Returns one time pin given a phone number.
     *
     * @param phoneNumber
     */
    public String retrieveOneTimePin(final String phoneNumber) throws IOException, ApiClientException {
        // send outgoing messages
        executeRequest(getRequest(sendOutgoingMessagesApiUrl));

        // retrieve outgoing message log
        HttpUrl httpUrl = outgoingMessageApiUrl.newBuilder()
                .addQueryParameter(PARAM_TO, phoneNumber)
                .addQueryParameter(PARAM_TEMPLATE, ONE_TIME_PIN_TEMPLATE)
                .build();
        Response response = executeRequest(getRequest(httpUrl));
        JSONArray jsonArray = extractJsonObject(response).getJSONArray(DATA_TAG);
        Iterator iterator = jsonArray.iterator();
        int id = -1;
        String message = null;
        while (iterator.hasNext()) {
            JSONObject item = new JSONObject(iterator.next().toString());
            if (id < item.getInt(ID)) {
                id = item.getInt(ID);
                message = item.getString(MESSAGE);
            }
        }
        return StringUtils.split(message, SPACE_DELIMITER)[0];
    }
}
