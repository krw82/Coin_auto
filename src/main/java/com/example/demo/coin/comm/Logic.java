package com.example.demo.coin.comm;


@FunctionalInterface
public interface Logic<T> {
    Double process(int item1);
}
