
/*
    Java IO编程
    管道流：管道流主要的功能是实现两个线程之间的IO处理操作
    管道流也分两类：
        字节管道流：PipedOutputStream、PipedInputStream
            连接处理：public void connect(PipedInputStream snk) throws IOException
        字符管道流：PipedWriter、PipedReader
            连接处理：public void connect(PipedReader snk) throws IOException
    范例：实现管道操作
        import java.io.PipedOutputStream;
        import java.io.PipedInputStream;
        import java.io.IOException;

        class SendThread implements Runnable {
            private PipedOutputStream output; // 管道的输出流

            public SendThread() {
                this.output = new PipedOutputStream(); // 实例化管道输出流
            }

            @Override
            public void run() {
                try { // 利用管道实现数据的发送处理
                    this.output.write(("【信息发送-" + Thread.currentThread().getName() + "】www.mldn.cn\n").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    this.output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public PipedOutputStream getOutput() {
                return output;
            }
        }

        class ReceiveThread implements Runnable {
            private PipedInputStream input;

            public ReceiveThread() {
                this.input = new PipedInputStream();
            }

            @Override
            public void run() {
                byte data[] = new byte[1024];
                try {
                    int len = this.input.read(data);
                    System.out.println("{" + Thread.currentThread().getName() + "接收消息}" + new String(data, 0, len));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    this.input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public PipedInputStream getInput() {
                return input;
            }
        }

        public class PipedStreamDemo {
            public static void main(String[] args) throws Exception {
                SendThread send = new SendThread();
                ReceiveThread receive = new ReceiveThread();
                send.getOutput().connect(receive.getInput()); // 进行管道连接
                new Thread(send, "消息发送线程").start();
                new Thread(receive, "消息接收线程").start();
            }
        }


    范例：下面实现循环接收的代码
        import java.io.PipedOutputStream;
        import java.io.PipedInputStream;
        import java.io.IOException;
        import java.io.ByteArrayOutputStream;

        class SendThread implements Runnable {
            private PipedOutputStream output; // 管道的输出流

            public SendThread() {
                this.output = new PipedOutputStream(); // 实例化管道输出流
            }

            @Override
            public void run() {
                for (int x = 0; x < 10; x++) {
                    try { // 利用管道实现数据的发送处理
                        this.output
                                .write(("【信息发送-" + (x + 1) + Thread.currentThread().getName() + "】www.mldn.cn\n").getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    this.output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public PipedOutputStream getOutput() {
                return output;
            }
        }

        class ReceiveThread implements Runnable {
            private PipedInputStream input;

            public ReceiveThread() {
                this.input = new PipedInputStream();
            }

            @Override
            public void run() {
                byte data[] = new byte[1024];
                int len = 0;
                ByteArrayOutputStream bos = new ByteArrayOutputStream(); // 所有的数据保存到内存输出流
                try {
                    while ((len = this.input.read(data)) != -1) {
                        bos.write(data, 0, len); // 所有的数据保存到内存流
                    }
                    System.out.println("{" + Thread.currentThread().getName() + "接收消息}\n" + new String(bos.toByteArray()));
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    this.input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public PipedInputStream getInput() {
                return input;
            }
        }

        public class PipedStreamDemo {
            public static void main(String[] args) throws Exception {
                SendThread send = new SendThread();
                ReceiveThread receive = new ReceiveThread();
                send.getOutput().connect(receive.getInput()); // 进行管道连接
                new Thread(send, "消息发送线程").start();
                new Thread(receive, "消息接收线程").start();
            }
        }

    管道就类似于医院打点滴的效果，一个只是负责发送，一个只是负责接收，中间靠一个管道进行连接
*/
import java.io.PipedOutputStream;
import java.io.PipedInputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;

class SendThread implements Runnable {
    private PipedOutputStream output; // 管道的输出流

    public SendThread() {
        this.output = new PipedOutputStream(); // 实例化管道输出流
    }

    @Override
    public void run() {
        for (int x = 0; x < 10; x++) {
            try { // 利用管道实现数据的发送处理
                this.output
                        .write(("【信息发送-" + (x + 1) + Thread.currentThread().getName() + "】www.mldn.cn\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PipedOutputStream getOutput() {
        return output;
    }
}

class ReceiveThread implements Runnable {
    private PipedInputStream input;

    public ReceiveThread() {
        this.input = new PipedInputStream();
    }

    @Override
    public void run() {
        byte data[] = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream(); // 所有的数据保存到内存输出流
        try {
            while ((len = this.input.read(data)) != -1) {
                bos.write(data, 0, len); // 所有的数据保存到内存流
            }
            System.out.println("{" + Thread.currentThread().getName() + "接收消息}\n" + new String(bos.toByteArray()));
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PipedInputStream getInput() {
        return input;
    }
}

public class PipedStreamDemo {
    public static void main(String[] args) throws Exception {
        SendThread send = new SendThread();
        ReceiveThread receive = new ReceiveThread();
        send.getOutput().connect(receive.getInput()); // 进行管道连接
        new Thread(send, "消息发送线程").start();
        new Thread(receive, "消息接收线程").start();
    }
}