package ch06.v1;

import java.net.http.HttpClient;

public class Test {

    HttpClient httpClient;

    public Test(HttpClient httpClient) {
        this.httpClient = httpClient;
    }


    void test() {
        httpClient.sendAsync()
    }


}
