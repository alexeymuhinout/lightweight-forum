package com.rustedbrain.web.controller.util;

import com.rustedbrain.web.model.servlet.SessionRequestContent;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContentUtil {
    private static SessionRequestContentUtil ourInstance = new SessionRequestContentUtil();

    private SessionRequestContentUtil() {
    }

    public static SessionRequestContentUtil getInstance() {
        return ourInstance;
    }

    public static void setAttribute(SessionRequestContent requestContent, String key, Object value) {
        requestContent.getRequestAttributes().put(key, value);
    }

    public static void setSessionAttribute(SessionRequestContent requestContent, String key, Object value) {
        requestContent.getSessionAttributes().put(key, value);
    }

    public SessionRequestContent newSessionRequestContent(HttpServletRequest request) {
        if (request == null)
            throw new IllegalArgumentException("HttpServletRequest can not be null");

        HashMap<String, Object> requestAttributes = new HashMap<>();
        HashMap<String, String[]> requestParameters = new HashMap<>();
        HashMap<String, Object> sessionAttributes = new HashMap<>();

        Enumeration attributesNames = request.getAttributeNames();
        while (attributesNames.hasMoreElements()) {
            String name = (String) attributesNames.nextElement();
            requestAttributes.put(name, request.getAttribute(name));
        }
        Enumeration parametersNames = request.getParameterNames();
        while (parametersNames.hasMoreElements()) {
            String name = (String) parametersNames.nextElement();
            requestParameters.put(name, request.getParameterValues(name));
        }
        Enumeration sessionAttributesNames = request.getSession().getAttributeNames();
        while (sessionAttributesNames.hasMoreElements()) {
            String name = (String) sessionAttributesNames.nextElement();
            sessionAttributes.put(name, request.getSession().getAttribute(name));
        }

        SessionRequestContent requestContent = new SessionRequestContent();
        requestContent.setRequestAttributes(requestAttributes);
        requestContent.setRequestParameters(requestParameters);
        requestContent.setSessionAttributes(sessionAttributes);

        return requestContent;
    }

    public void extractValuesToRequest(HttpServletRequest servletRequest, SessionRequestContent requestContent) {
        HashMap<String, Object> requestAttributes = requestContent.getRequestAttributes();
        for (Map.Entry<String, Object> entry : requestAttributes.entrySet()) {
            servletRequest.setAttribute(entry.getKey(), entry.getValue());
        }
        HashMap<String, String[]> requestParameters = requestContent.getRequestParameters();
        for (Map.Entry<String, String[]> entry : requestParameters.entrySet()) {
            servletRequest.setAttribute(entry.getKey(), entry.getValue());
        }
        HashMap<String, Object> sessionAttributes = requestContent.getSessionAttributes();
        for (Map.Entry<String, Object> entry : sessionAttributes.entrySet()) {
            servletRequest.getSession().setAttribute(entry.getKey(), entry.getValue());
        }

        if (requestContent.isInvalidatedSession()) {
            servletRequest.getSession().invalidate();
        }
    }
}
