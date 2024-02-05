import java.io.*;
import java.net.*;
import org.json.*;

public class TCPClient {
    // サーバーのIPアドレスとポート番号
    static String serverIPAddress = "172.24.20.125";
    static int serverPort = 52001;

    // クライアントのIPアドレス
    static String myIPAddress;

    // TCPソケット
    static Socket tcpSocket;

    public static void main(String[] args) {
        try {
            // クライアントのIPアドレスを取得
            myIPAddress = InetAddress.getLocalHost().getHostAddress();
            System.out.println(myIPAddress);

            // TCPソケットの作成とサーバーへの接続
            tcpSocket = new Socket(serverIPAddress, serverPort);

            // メッセージを定期的に送信するスレッドの開始
            Thread sendThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        net(myIPAddress, "enemy", "coor", "1.11", "none");
                    } catch (Exception e) {
                        System.out.println("Error in send_messages: " + e.getMessage());
                    }
                }
            });
            sendThread.start();

            // コネクションを維持するスレッドの開始
            Thread keepThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            Thread.sleep(5000); // 5秒ごとに何かアクションをする（この例では何も行っていない）
                        }
                    } catch (Exception e) {
                        System.out.println("Error in keep_connection: " + e.getMessage());
                    }
                }
            });
            keepThread.start();

            // メッセージを受信するスレッドの開始
            Thread receiveThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream(), "UTF-8"));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println("Received message: " + line);
                        }
                    } catch (IOException e) {
                        System.out.println("Error in receiving messages: " + e.getMessage());
                    } 
                }
            });
            receiveThread.start();

        } catch (IOException e) {
            System.out.println("Error in connecting to server: " + e.getMessage());
        }
    }

    // クライアント側の net 関数
    static void net(String host, String character, String datatype, String data, String message) throws IOException {
        JSONObject datadic = new JSONObject();
        datadic.put("host", host);
        datadic.put("character", character);
        datadic.put("datatype", datatype);
        datadic.put("data", data);
        datadic.put("message", message);

        // JSON形式にエンコードしてから送信
        String jsonString = datadic.toString();
        System.out.println(jsonString);

        OutputStream outputStream = tcpSocket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(jsonString);
        writer.flush();
    }
}

