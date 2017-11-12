package ru.geekbrains.textbrowser;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

// Делатель запросов
public class RequestMaker {
    private Display display;

    public RequestMaker(Display display) {
        this.display = display;
    }

    public void Make(String url) {
        RequestQueue queue = Volley.newRequestQueue(display.getContext());

        // Формируем запрос, метод GET, uri в качестве параметра
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    //когда приходит ответ на запрос
                    @Override
                    public void onResponse(String response) {
                        // покажем текст страницы
                        display.setDisplayText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        display.setDisplayText("Error!");
                    }
                });
        // выполним запрос
        queue.add(stringRequest);
    }
}
