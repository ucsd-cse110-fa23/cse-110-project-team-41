package main.java;

import java.io.File;

public class MockWhisper implements IWhisper {

    @Override
    public String connect(File args) {
        return "Give me a Lunch recipe that I can make with Chicken, rice, and beans.";
    }
}
