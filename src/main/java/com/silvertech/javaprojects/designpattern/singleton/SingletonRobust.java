package com.silvertech.javaprojects.designpattern.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

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
public class SingletonRobust implements Serializable, Cloneable {
  public static volatile SingletonRobust soulInstance = null;
  private SingletonRobust() throws Exception {
    if(soulInstance != null)throw new Exception("Please use getInstance() to get instance!");
  }

  //double check lock
  public static SingletonRobust getInstance() throws Exception {
    if(soulInstance == null){
      synchronized (SingletonRobust.class) {
        if(soulInstance == null)
        soulInstance = new SingletonRobust();
      }
    }
    return soulInstance;
  }

  private Object readResolve() throws ObjectStreamException {
    return soulInstance;
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return soulInstance;
  }
}
