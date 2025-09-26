package net.eclearing.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class ChatApi {

    //was static und was nicht?
    //kontextabhänig, funktionen die http aus führen oder daten aus uri liefern non-static
    //functionen die nur JSON verarbeiten speichern kleinen zustand und hängen nicht von http ab. static

    private final String baseUri;
    private final HttpClient client;

        public ChatApi(String pBaseUri){
            this.baseUri = pBaseUri;
            this.client = HttpClient.newHttpClient();
        }


//########################################################################################################################################################################################################################################
//############################ erstmal http request verarbeiten ##########################################################################################################################################################################
//########################################################################################################################################################################################################################################

        //return "the hole site/URI"
        public HttpResponse getResponse() throws IOException, InterruptedException {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUri))  //das sollte im optimal fall dynamisch sein, wir haben aber keine passenden daten gklaueb ich
                    .GET()
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString());

        }

        public String getResponseBody() throws IOException, InterruptedException {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUri))  //das sollte im optimal fall dynamisch sein, wir haben aber keine passenden daten gklaueb ich
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        }

//########################################################################################################################################################################################################################################
//############################ von http request zu Json array ############################################################################################################################################################################
//########################################################################################################################################################################################################################################

/*
noch alles static, kann man aber ändern
 */

        //converts the HTTP request to Json Object
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


//########################################################################################################################################################################################################################################
//########################## arbeiten mit den einzelnen nachrichten, nicht allen gleichzeiotig ## Get info of msg ########################################################################################################################
//########################################################################################################################################################################################################################################

        //im folgenden teil gehe ich davon auss dass ich auf einer einzelden nachricht (aus dem Json Array) arbeite

        public static JsonObject convertJsonElementToJsonObject(JsonElement pElement){//pElement ist in unserem fall eine einzelnde nachricht
            return pElement.getAsJsonObject();
        }

        //###################get die einzelden felder einer nachricht ############################################################################################################################################################################

        public static String getMsgID(JsonObject pMessage){
            return pMessage.get("id").getAsString();
        }

        public static String getCreatedDateTime(JsonObject pMessage){
            return pMessage.get("createdDateTime").getAsString();
        }
        //----------------verarbeite body----------------------------------------

        //das feld "Body" ist nochmal ein eigenes Json Object, ich will auf alle teile zugreifen können
        public static JsonObject getBody(JsonObject pMessage){
            return pMessage.get("body").getAsJsonObject();
        }

        public static String getContentType(JsonObject pMessage){
            JsonObject body = getBody(pMessage);
            return body.get("contentType").getAsString();

        }

        public static String getContent(JsonObject pMessage){
            JsonObject body = getBody(pMessage);
            return body.get("content").getAsString();

        }


        //----------------verarbeite from----------------------------------------
        //das feld "from" ist nochmal ein eigenes Json Object, ich will auf alle teile zugreifen können
        public static JsonObject getFromMsg(JsonObject pMessage){
            return convertJsonElementToJsonObject(pMessage.get("from"));
        }

        public static JsonObject getUser(JsonObject pMessage){
            JsonObject from = getFromMsg(pMessage);
            return convertJsonElementToJsonObject(from.get("user"));
        }

        public static String getSenderId(JsonObject pMessage){
            JsonObject user = getUser(pMessage);
            return user.get("id").getAsString();
        }

        public static String getSenderName(JsonObject pMessage) {
            JsonObject user = getUser(pMessage);
            return user.get("displayName").getAsString();
        }
        //---------------------nachricht vertig verarbeitet



        //soll den text einer nachricht ausgeben, ohne zusätzliche infos
        public void getAllMsg() throws IOException, InterruptedException {

            JsonArray messages = getMessagesAsArray();
            System.out.println("###messages###:"+messages);


            for (JsonElement element : messages) {
                System.out.println("________________________________________________________");

                System.out.println("1111111111element111111111"+element);

                JsonObject msg = element.getAsJsonObject();

                String msgId = getMsgID(msg);
                System.out.println("22222222222msg222222222"+msg);
                System.out.println("22222222222id222222222"+msgId);

                JsonObject from = getFromMsg(msg);
                JsonObject user = getUser(msg);
                String displayName = getSenderName(msg);
                System.out.println("333333333from3333333333"+from );
                System.out.println("333333333User3333333333"+from );
                System.out.println("333333333displayName33333333333"+displayName);

                JsonObject body = getBody(msg);
                String contentType = getContentType(msg);
                String content = getContent(msg);

                System.out.println("######body#########"+body);
                System.out.println("######content Type#########"+contentType);

                System.out.println("######content #########"+content);


                System.out.println(displayName + "#:# " + content); //das was man tatsächlich exportieren will


            }
        }
    }

/*
beispiel use:

ChatApi chatApi = new ChatApi("http://localhost:8181/v1.0/chats/19:devteam-2025@thread.v2/messages");

try {
    JsonArray messages = chatApi.getMessagesAsArray();
    for (JsonElement e : messages) {
        JsonObject msg = ChatApi.convertJsonElementToJsonObject(e);
        String sender = ChatApi.getSenderName(msg);
        String content = ChatApi.getContent(msg);

        System.out.println(sender + ": " + content);//oder fülle label oder so
    }
} catch (IOException | InterruptedException ex) {
    ex.printStackTrace();
}
 */
