package edu.jespinoza.websocket;

import javafx.scene.control.TextArea;

import javax.websocket.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;

public class ChatroomClientEndpoint extends Endpoint {
    private Session session;

    public ChatroomClientEndpoint(TextArea textArea) throws URISyntaxException, IOException, DeploymentException {
        // TODO poner esto en un archivo de Propiedades
        String host = "localhost";
        String port = "8080";
        String pathContext = "/WebSocketBroadcastServer/";
        String nameService = "chat/";
        // *******************************************
        InetAddress IP = InetAddress.getLocalHost();
        String uriString = "ws://" + host + ":" + port
                + pathContext + nameService + IP.getHostAddress();
        URI uri = new URI(uriString);
        ContainerProvider.getWebSocketContainer().connectToServer(this, uri);
        session.addMessageHandler(new ChatroomMessageHandler(textArea));
    }

    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        this.session = session;
    }

    public void sendMessage(String message) throws IOException {
        session.getBasicRemote().sendText("{\"content\" : \"" + message + "\"}");
    }
}
