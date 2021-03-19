package edu.jespinoza;

import edu.jespinoza.websocket.ChatroomClientEndpoint;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("WebSocket Broadcast");
        final TextField textField = new TextField();
        Button sendButton = new Button("Send");
        HBox hBox = new HBox();
        hBox.getChildren().addAll(textField, sendButton);
        TextArea textArea = new TextArea();
        textArea.setFocusTraversable(false);
        textArea.setEditable(false);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(textArea, hBox);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(vBox);
        stage.setScene(new Scene(stackPane, 600, 200));

        final ChatroomClientEndpoint chatroomClientEndpoint =
                new ChatroomClientEndpoint(textArea);

        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    chatroomClientEndpoint.sendMessage(textField.getText());
                    textField.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        stage.show(); // display the stage
    }
}