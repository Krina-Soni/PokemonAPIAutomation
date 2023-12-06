package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtils {

    static RequestSpecification reqSpec = RestAssured.given();
    static Response response;
    static String endPoint;

    public static void setEndPoint(String endPoint){
        RestAssuredUtils.endPoint = endPoint;
    }

    public static void setBody(String body) {
        reqSpec = reqSpec.body(body);
    }

    public static void setBody(Object obj) {
        reqSpec = reqSpec.body(obj);
    }

    public static void setHeader(String key, String value) {
        reqSpec = reqSpec.header(key, value);
    }

    public static void post() {
        response = reqSpec.log().all().post(endPoint);
        response.then().log().all();
    }

    public static void get() {
        response = reqSpec.log().all().get(endPoint);
        response.then().log().all();
    }

    public static int getStatusCode(){
        return response.getStatusCode();
    }

    public static String getResponseBody(){
        return response.asString();
    }

    public static Response getResponse(){
        return response;
    }
}
