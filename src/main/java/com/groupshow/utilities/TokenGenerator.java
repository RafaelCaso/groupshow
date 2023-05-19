package com.groupshow.utilities;

import java.util.UUID;

public class TokenGenerator {

    public static String createNewToken() {
        return UUID.randomUUID().toString();
    }
}
