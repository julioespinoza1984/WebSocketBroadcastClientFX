package edu.jespinoza.websocket;

import javafx.scene.control.TextArea;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.MessageHandler;
import java.io.StringReader;

public class ChatroomMessageHandler implements MessageHandler.Whole<String> {
    TextArea textArea;

    public ChatroomMessageHandler(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void onMessage(String message) {
        StringReader stringReader = new StringReader(message);
        JsonReader jsonReader = Json.createReader(stringReader);
        JsonObject jsonObject = jsonReader.readObject();
        textArea.appendText(jsonObject.getString("from"));
        textArea.appendText(": ");
        textArea.appendText(jsonObject.getString("content"));
        textArea.appendText("\n");
    }
}
