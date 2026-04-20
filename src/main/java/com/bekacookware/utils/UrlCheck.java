package com.bekacookware.utils;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class UrlCheck {

        public Integer brokenUrlAndImageCheck(String url) {
            RestAssured.baseURI = url;
            Response response = RestAssured.get();
            return response.statusCode();
        }
    }

