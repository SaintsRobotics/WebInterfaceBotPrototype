package com.saintsrobotics.webinterfacebot;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class TestWebSocket extends WebSocketServer {

	public TestWebSocket() throws UnknownHostException {
		super(new InetSocketAddress(1899));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		conn.send("{'name':'hi','type':'input','data':'hi'}");
		conn.send("{'name':'bye','type':'output','data':'bye'}");
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		conn.send(message);
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		// TODO Auto-generated method stub
		
	}

}
