package com.study.array;

import com.study.array.ArrayQueue;
import com.study.array.Queue;

public class ArrayQueueTest {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue();
        process(arrayQueue);
    }

    static void process(Queue queue) {
        queue.enqueue("A");
        queue.enqueue("B");
        System.out.println(queue.size()); // 2

        Object value = queue.dequeue();
        System.out.println(value); // A

        System.out.println(queue.size()); // 1
        System.out.println(queue.peek()); // B
        System.out.println(queue.size()); // 1

        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }

        System.out.println(queue.size()); // 6
    }
}