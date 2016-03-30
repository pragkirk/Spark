package com.kirkk.hello;

import static spark.Spark.*;
import com.google.gson.Gson;
import static com.kirkk.hello.JsonUtil.*;
 
public class HelloSpark {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello, World!");
        get("/loan", "application/json", (req, res) -> {
        	String principle = req.queryParams("principle");
        	String rate = req.queryParams("rate");
        	String term = req.queryParams("term");
        	/*String rate = "6.0";
        	String term = "60";*/
        	
        	return new Payment().setPayment(new LoanCalculator().calculatePayment(principle, rate, term));
        }, json());
        
        /*get("/loan", (req, res) -> {        	
        	return new Payment().setPayment(new LoanCalculator().calculatePayment("15000","6.0","60"));
        }, json());*/
    }
}