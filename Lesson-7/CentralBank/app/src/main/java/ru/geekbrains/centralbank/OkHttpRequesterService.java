package ru.geekbrains.centralbank;

import android.os.Handler;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static junit.framework.Assert.fail;

public class OkHttpRequesterService {
    private OkHttpClient client;
    private final OnCompletedRequest listener;
    // сайт с веб-сервисом
    private final static String urlCbr = "http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx";
    // тип данных в теле запроса
    private static final MediaType SOAP_MEDIA_TYPE = MediaType.parse("text/xml");

    public OkHttpRequesterService(OnCompletedRequest listener) {
        client = new OkHttpClient();
        this.listener = listener;
    }

    public void run() {

        /*
        Формат запроса веб сервиса указан здесь http://www.cbr.ru/DailyInfoWebServ/DailyInfo.asmx?op=SwapDynamicXML
         */
        // Тело запроса
        String soap_string = "<?xml version=\"1.0\" encoding=\"utf-8\"?> " +
                " <soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                " <soap:Body>" +
                " <SwapDynamicXML xmlns=\"http://web.cbr.ru/\">" +
                " <fromDate>2017-11-03T00:00:00</fromDate>" +
                " <ToDate>2017-11-03T00:00:00</ToDate>" +
                " </SwapDynamicXML>" +
                " </soap:Body>" +
                " </soap:Envelope>";
        RequestBody body = RequestBody.create(SOAP_MEDIA_TYPE, soap_string);
        // Формируем запрос
        Request request = new Request.Builder()
                .url(urlCbr)
                .post(body)
                .addHeader("Content-Type", "text/xml; charset=utf-8")
                .addHeader("SOAPAction", "http://web.cbr.ru/SwapDynamicXML")
                .addHeader("cache-control", "no-cache")
                .build();

        Call call = client.newCall(request);
        final Handler handler = new Handler();
        // ставим запрос в очередь, после чего она начинает выполнятся
        call.enqueue(new Callback() {
            // запрос вернул данные
            @Override
            public void onResponse(Call call, Response response)
                    throws IOException {
                final String answer = response.body().string(); // получить данные
                // синхронизация с UI
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onCompletedRequest(answer);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                fail();
            }
        });
    }

    public interface OnCompletedRequest {
        void onCompletedRequest(String string);
    }
}
