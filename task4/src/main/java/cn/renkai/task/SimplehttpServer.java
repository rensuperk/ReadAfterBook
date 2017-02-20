package cn.renkai.task;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by renkai on 2017/2/18.
 */
public class SimplehttpServer {
    static String basePath;
    static ServerSocket serverSocket;
    static int port = 8080;
    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<HttpRequestHandler>(1);

    public static void setBasePath(String basePath) {
        if(basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()){

            SimplehttpServer.basePath = basePath;
        }
    }

    public static void setPort(int port) {
        if(port>0){

            SimplehttpServer.port = port;
        }
    }

    public static void main(String[] args) throws IOException {
        start();
    }
    public static void start() throws IOException {
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while ((socket = serverSocket.accept()) != null){
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }
    static class HttpRequestHandler implements Runnable{
        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader =null;
            PrintWriter out = null;
            InputStream in = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                if(filePath.endsWith("jpg") || filePath.endsWith("ico")){
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i =0;
                    while ((i = in.read()) != -1){
                        baos.write(i);
                    }
                    byte[] array = baos.toByteArray();
                    out.print("HTTP/1.1 200 OK");
                    out.print("Server: Molly");
                    out.print("Content_type: image/jpeg");
                    out.print("Content-length: "+array.length);
                    out.print("");
                    socket.getOutputStream().write(array,0,array.length);
                }else {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
                    out.print("HTTP/1.1 200 OK");
                    out.print("Server: Molly");
                    out.print("Content_type: text/html; charset=UTF-8");
                    out.print("");
                    while ((line = br.readLine()) != null){
                        out.print(line);
                    }
                    out.flush();
                }
            } catch (IOException e) {
                out.print("HTTP/1.1 500");
                out.print("");
                out.flush();
            }finally {
                close(br,in,reader,out,socket);
            }
        }
        private static void close(Closeable... closeables){
            if(closeables !=null){
                for (Closeable closeable : closeables) {
                    try {
                        closeable.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
