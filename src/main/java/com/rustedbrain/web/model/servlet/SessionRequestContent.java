package com.rustedbrain.web.model.servlet;

import java.util.HashMap;

public class SessionRequestContent {

    private HashMap<String, Object> requestAttributes = new HashMap<>();
    private HashMap<String, String[]> requestParameters = new HashMap<>();
    private HashMap<String, Object> sessionAttributes = new HashMap<>();

    public HashMap<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public void setRequestAttributes(HashMap<String, Object> requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    public HashMap<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(HashMap<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public HashMap<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public void setSessionAttributes(HashMap<String, Object> sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }
}
