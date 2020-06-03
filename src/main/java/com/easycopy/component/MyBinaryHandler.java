package com.easycopy.component;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class MyBinaryHandler extends BinaryWebSocketHandler {
    Map<WebSocketSession, List<ByteBuffer>> sessionToFileMap = new WeakHashMap<>();

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        try {
            System.out.println("Receiving binary info...." + message);
            System.out.println("Att:  " + session.getAttributes());
            System.out.println("Sessions:  " + sessionToFileMap.size());
            ByteBuffer payload = message.getPayload();
            sessionToFileMap.computeIfAbsent(session, k -> new ArrayList<>());
            sessionToFileMap.get(session).add(payload);

            if (message.isLast()) {
                Path basePath = Paths.get(".", "uploads", UUID.randomUUID().toString());
                Files.createDirectories(basePath);
                FileChannel channel = new FileOutputStream(
                        Paths.get(basePath.toString(), "ABC").toFile(), false).getChannel();

                List<ByteBuffer> byteBuffers = sessionToFileMap.get(session);
                channel.write(byteBuffers.toArray(new ByteBuffer[byteBuffers.size()]));
                channel.close();
                //session.sendMessage(new TextMessage("UPLOAD " + inflightUpload.name));
                session.close();
                sessionToFileMap.remove(session);
            }
            String response = "Upload Chunk: size " + payload.array().length;
            System.out.println(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}