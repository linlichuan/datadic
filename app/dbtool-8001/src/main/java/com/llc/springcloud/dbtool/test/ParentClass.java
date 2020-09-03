package com.llc.springcloud.dbtool.test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public abstract class ParentClass {
    int a = 0;

    public static int b = 1;

    public static final int c = 1;

    public ParentClass(int i){
        a = i;
        printA();
    }

    public void printA(){
        System.out.println("parent class");
    }

    public void fun(){
              Thread t2 = new Thread(() -> {
            try {
                SocketChannel channel = SocketChannel.open();
                channel.configureBlocking(false);
                channel.connect(new InetSocketAddress("localhost",5555));
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                Selector selector = Selector.open();
                channel.register(selector, SelectionKey.OP_CONNECT);

                int i = 1;
                while (true){
                    System.out.println("client selector.select() : " + selector.select());
                    Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                    while (it.hasNext()){
                        SelectionKey key = it.next();
                        it.remove();
                        System.out.println("key.isConnectable() : " + key.isConnectable());
                        System.out.println("key.isWritable() : " + key.isWritable());
                        if (key.isValid() && key.isConnectable()){
                            SocketChannel ch = (SocketChannel) key.channel();
                            if (ch.isConnectionPending()) {
                                ch.finishConnect();
                            }
                            ch.configureBlocking(false);
                            ch.register(selector,SelectionKey.OP_WRITE);
                        }
                        if (key.isValid() && key.isWritable()){
                            SocketChannel ch = (SocketChannel) key.channel();
                            ch.configureBlocking(false);
                            if (ch.isConnected()){
                                String text = (i++) + "：public abstract Selector selector():返回此选择键所关联的选择器,即使此键已经被取消,仍然会返回.";
                                for (int j = 0; j < text.length(); j++) {
                                    buffer.putChar(text.charAt(j));
                                }
                                ch.write(buffer);
                                buffer.clear();
                                Thread.sleep(1000);
                            }
                        }
                    }
                }


            }catch (Exception e){
                e.printStackTrace();
            }
        });
        t2.start();
    }

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        while (true){
            String next = scanner.next();
            if ("q".equals(next) || "Q".equals(next)){
                break;
            }

            if (!next.endsWith(".com")){
                System.out.println("incorrect email format");
                continue;
            }

            if (next.indexOf("@") == 0){
                System.out.println("incorrect email format");
                continue;
            }

            String userName = next.substring(0,next.indexOf("@"));
            String companyName = next.substring(next.indexOf("@") + 1,next.lastIndexOf(".com"));

            System.out.println("userName = " + userName + "; companyName = " + companyName);
        }
    }
}
