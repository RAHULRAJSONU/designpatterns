package com.silvertech.javaprojects.designpattern.producerconsumer;

import com.silvertech.javaprojects.designpattern.producerconsumer.waitnotify.CustomBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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
public class Application {

  public static void main(String[] args) throws InterruptedException {
//    CustomBlockingQueue<String> queue = new CustomBlockingQueue<>(5);
    BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    Runnable producer = ()->{
      int count = 0;
      while (true){
        queue.add("Task->"+count);
        count++;
      }
    };

    new Thread(producer).start();
    new Thread(producer).start();

    Runnable consumer = ()->{
      while (true){
        String task = null;
        try {
          task = queue.take();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("Processed task:: "+task);
      }
    };

    new Thread(consumer).start();
//    new Thread(consumer).start();
    Thread.sleep(1000);
  }

}
