package com.springtest;

import org.springframework.stereotype.Repository;

@Repository(value = "Test")
public class TestImpl implements Test {
    @Override
    public void update() {
        System.out.println("update...");
    }

    @Override
    public void search() {
        System.out.println("search...");
    }
}
