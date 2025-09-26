package net.eclearing.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiHttpRequest {
    private final String baseUri;
    private final HttpClient client;

    public ApiHttpRequest(String pBaseUri){
        this.baseUri = pBaseUri;
        this.client = HttpClient.newHttpClient();
    }

/*
    //return "the hole site/URI"
    public HttpResponse getResponse() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUri))  //das sollte im optimal fall dynamisch sein, wir haben aber keine passenden daten gklaueb ich
                .GET()
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());

    }

 */

    public String getResponseBody() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUri))  //das sollte im optimal fall dynamisch sein, wir haben aber keine passenden daten gklaueb ich
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();

    }

    //converts the HTTP response to Json Object
    public JsonObject responseToJsonObject() throws IOException, InterruptedException {
        String responseBody = getResponseBody();
        JsonElement root = JsonParser.parseString(responseBody);
        return root.getAsJsonObject();
    }


    //so wie unsere daten strukturiert sind, gibt es hinter "value" im JSON die einzelnen daten. solange sich an der struktur nicht änder sollte das so passen
    public JsonArray getMessagesAsArray() throws IOException, InterruptedException {
        //"value", da hier die nachrichten liegen
        return responseToJsonObject().getAsJsonArray("value"); //jetzt hat man einen Array in denen die nachrichten als JSON gespeichert sind
    }

}
