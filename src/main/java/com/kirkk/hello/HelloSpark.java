package com.kirkk.hello;

import static spark.Spark.*;
import com.google.gson.Gson;
import static com.kirkk.hello.JsonUtil.*;
 
public class HelloSpark {
    public static void main(String[] args) {
    	port(getHerokuAssignedPort());
        get("/hello", (req, res) -> "Hello, World!");
        get("/loan", "application/json", (req, res) -> {
        	String principle = req.queryParams("principle");
        	String rate = req.queryParams("rate");
        	String term = req.queryParams("term");        	
        	return new Payment().setPayment(new LoanCalculator().calculatePayment(principle, rate, term));
        }, json());
    }
    
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}