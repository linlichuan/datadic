package com.springboot.dbtool.test;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class ChildClass extends ParentClass  implements Externalizable{
    String s;

    public ChildClass(){
        super(1);
        printA();
    }

    public void editA(int i){
        a = i;
        b = i;
    }

    public void printA(){
        System.out.println("child class");
    }

    public int testException(boolean isExp){
        try {
            System.out.println("try");
            if (isExp){
                throw new RuntimeException();
            }
            return 1;
        }catch (Exception e){
            System.out.println("catch");
            return 2;
        }finally {
            System.out.println("finally");
            return 3;
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(s);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        s = (String)in.readObject();
    }

    public void getData(InputStream ips){

    }

    public static void main(String[] args){

        Thread t1 = new Thread(() ->{
            try {

                Selector selector = Selector.open();
                ServerSocketChannel channel = ServerSocketChannel.open();
                channel.configureBlocking(false);
                ServerSocket serverSocket = channel.socket();
                serverSocket.bind(new InetSocketAddress(5555));
                channel.register(selector,SelectionKey.OP_ACCEPT);
                while (true){
                    System.out.println("server selector.select() : " + selector.select());
                    Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                    while (it.hasNext()){
                        SelectionKey key = it.next();
                        it.remove();
                        System.out.println("key.isAcceptable() : " + key.isAcceptable());
                        System.out.println("key.isReadable() : " + key.isReadable());
                        if (key.isValid() && key.isAcceptable()){
                            ServerSocketChannel c = (ServerSocketChannel) key.channel();
                            SocketChannel ch = c.accept();
                            ch.configureBlocking(false);
                            ch.register(selector,SelectionKey.OP_READ);
                        }
                        if (key.isValid() && key.isReadable()) {
                            SocketChannel c = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            System.out.println("read : " + c.read(buffer));
                            System.out.println(new String(buffer.array()).trim());
                        }
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });



        t1.start();

    }
}
