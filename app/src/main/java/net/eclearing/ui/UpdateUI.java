package net.eclearing.ui;

public class UpdateUI extends UIMain {
    UpdateUI(String date, String user, String message){
        viewField.append("(" + date + ") " + user + ": " + message + "\n");
    }
    void badLink(String link, String errorMessage) {
        viewField.setText("An error has occured: " + errorMessage + " (" + link + ")");
    }
}
