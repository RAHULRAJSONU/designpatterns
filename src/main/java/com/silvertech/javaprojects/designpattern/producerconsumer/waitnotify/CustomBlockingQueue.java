package com.silvertech.javaprojects.designpattern.producerconsumer.waitnotify;

import java.util.LinkedList;
import java.util.Queue;

/*
 * design-pattern
 * MIT License
 *
 * Copyright (c) 2020 Rahul Raj
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class CustomBlockingQueue<T> {
  private int max;
  private Queue<T> queue;
  public CustomBlockingQueue(int size){
    this.max =size;
    this.queue = new LinkedList<>();
  }

  public void insert(T item) throws InterruptedException {
    synchronized (queue){
      while (this.queue.size()==this.max){
        try{
          System.out.println("{Queue::full} - waiting for consumer to consume the task");
          queue.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      queue.add(item);
      queue.notifyAll();
    }
    Thread.sleep(100);
  }

  public T take() throws InterruptedException {
    synchronized (queue){
      while (this.queue.size()==0){
        try {
          System.out.println("{Queue::empty} - waiting for producer to produce the task");
          queue.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      T item = queue.remove();
      queue.notifyAll();
      return item;
    }
  }
}
