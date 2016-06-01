package com.saintsrobotics.webinterfacebot;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.util.Hashtable;
import java.util.Map;
import org.json.JSONStringer;

public class WebInterface extends WebSocketServer {
	
	Map<String, String> table = new Hashtable<>();
	
	public WebInterface() throws UnknownHostException {
		super(new InetSocketAddress(1899));
		System.out.println("Ay");
		// TODO Auto-generated constructor stub
	}
	public void sendData(String name, String data, boolean input){
		this.connections().iterator().next().send("{'name':'%s','type':'%s','data':'%s'}".format(name,input?"input":"output",data).replace('\'', '"'));
	}
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		System.out.println("open");
		conn.send("{'name':'hi','type':'input','data':'hi'}".replace('\'', '"'));
		conn.send("{'name':'bye','type':'output','data':'bye'}".replace('\'', '"'));
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println("close");
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		System.out.println("recv: " + message);
		JSONStringer
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		System.out.println("ERROR: " + ex.getMessage());
		ex.printStackTrace();
		// TODO Auto-generated method stub
		
	}

}
