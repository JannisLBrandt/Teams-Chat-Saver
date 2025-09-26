package net.eclearing.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

public class ApiChatParser {

//########################################################################################################################################################################################################################################
//########################## arbeiten mit den einzelnen nachrichten, nicht allen gleichzeiotig ## Get info of msg ########################################################################################################################
//########################################################################################################################################################################################################################################

    //im folgenden teil gehe ich davon auss dass ich auf einer einzelden nachricht (aus dem Json Array) arbeite

    public static JsonObject convertJsonElementToJsonObject(JsonElement pElement){//pElement ist in unserem fall eine einzelnde nachricht,|| langfristig überflüssig, elemnte direkt per .getAsJsonObject aufrufen
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

}
