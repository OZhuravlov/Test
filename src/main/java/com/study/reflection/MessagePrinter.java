package com.study.reflection;

public class MessagePrinter {
    String message;

    @InjectRandomInt(min = 5, max = 10)
    int count;

    @InjectRandomInt(max = 10_000)
    int suffix;

    public MessagePrinter(String message) {
        this.message = message;
    }

    void print() {
        for (int i = 0; i < count; i++) {
            System.out.println(message + suffix);
        }
    }
}