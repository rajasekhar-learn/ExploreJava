package com.samples.streams;

public interface DAO<T> {
   T read();
   void write(T t);
   boolean remove(T t);
   void clear();
}
