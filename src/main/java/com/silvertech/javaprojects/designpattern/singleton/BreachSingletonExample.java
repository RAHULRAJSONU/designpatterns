package com.silvertech.javaprojects.designpattern.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
public class BreachSingletonExample {

  public static void main(String[] args) throws Exception {
    SingletonExample st1 = SingletonExample.getInstance();
    SingletonExample st2 = SingletonExample.getInstance();
    print("st1",st1);
    print("st2",st2);
//    SingletonExample st3 = (SingletonExample) breakByReflection();
    SingletonExample st3 = (SingletonExample) breakBySerAndDser(st2);
    print("st3",st3);
  }

  private static Object breakByReflection() throws Exception {
    Class clazz = Class.forName("com.silvertech.javaprojects.designpattern.singleton.SingletonExample");
    Constructor ctr = clazz.getDeclaredConstructor();
    ctr.setAccessible(true);
    return ctr.newInstance();
  }

  private static Object breakBySerAndDser(SingletonExample instance) throws Exception{
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/temp/output.ser"));
    oos.writeObject(instance);
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/temp/output.ser"));
    return ois.readObject();
  }

  private static void print(String name, SingletonExample obj) {
    System.out.println(String.format("object: %s, hashcode: %d",name,obj.hashCode()));
  }
}
