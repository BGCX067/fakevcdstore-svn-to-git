package com.thoughtworks.seleniumtest.testhelper;


public final class WebTestingServer {
    private WebTestingServer() {
    }

    public static void main(String[] args) throws Exception {
        new AppServer().start();
    }
}