package com.ssafy.tott.member.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MemoFactory {
    private final Random random = new Random();

    public String generateMemo() {
        int key = random.nextInt(1000) + 1;
        return key + " TOTT";
    }
}
