import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;
import java.net.Socket;
import java.net.*;

public class Client {
	Socket client;

	public Client() {

		try {
			// 서버 ip , 서버 port
			client = new Socket("127.0.0.1", 8080);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openConnection() {
		try {
			// 1. 자원 요청
			OutputStream os = client.getOutputStream();
			InputStream is = client.getInputStream();

			String uri = "GET /bbb.jsp HTTP/1.1 \r\n";
			uri += "Host: 127.0.0.1:8080 \r\n";
			uri += "Connection: keep.alive \r\n";
			uri += "Accept: */* \r\n";
					
			os.write(uri.getBytes());
			os.flush();

			// 2. 응답 확인
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = "";
			while ((line = br.readLine()) != null) {
				System.out.print(line + "\r\n");
			}
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
