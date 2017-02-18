package cn.renkai.task;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * Created by renkai on 2017/2/18.
 */
public class Piped {
    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        out.connect(in);
        Thread print = new Thread(new Print(in), "print");
        print.start();
        int receive = 0;
        try {

            while ((receive = System.in.read()) != -1){
                out.write(receive);
            }
        }finally {
            out.close();
        }
    }

    static class Print implements Runnable{
        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        public void run() {
            int receive = 0;
            try {
                while ((receive = in.read()) != -1){
                    System.out.print((char) receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
