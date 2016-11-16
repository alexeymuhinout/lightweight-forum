package com.rustedbrain.web.controller.command.util;

import com.rustedbrain.web.controller.command.RequestParameters;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.ProxyUser;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class CommandUtil {

    public static class Message {
        public static String getText(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.MESSAGE_TEXT_PARAMETER.getParameterName())) {
                return requestContent.getRequestParameters().get(RequestParameters.MESSAGE_TEXT_PARAMETER.getParameterName())[0];
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.message.text.null"));
            }
        }

        public static Integer getReceiverId(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.MESSAGE_RECEIVER_ID_PARAMETER.getParameterName())) {
                return Integer.parseInt(requestContent.getRequestParameters().get(RequestParameters.MESSAGE_RECEIVER_ID_PARAMETER.getParameterName())[0]);
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.message.receiver.null"));
            }
        }

        public static Integer getId(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.MESSAGE_ID_PARAMETER.getParameterName())) {
                return Integer.parseInt(requestContent.getRequestParameters().get(RequestParameters.MESSAGE_ID_PARAMETER.getParameterName())[0]);
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.message.id.not.found"));
            }
        }
    }

    public static class Subcategory {

        public static String getName(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.SUBCATEGORY_NAME_PARAMETER.getParameterName())) {
                return requestContent.getRequestParameters().get(RequestParameters.SUBCATEGORY_NAME_PARAMETER.getParameterName())[0];
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.subcategory.name.not.found"));
            }
        }

        public static Integer getId(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.SUBCATEGORY_ID_PARAMETER.getParameterName())) {
                return Integer.parseInt(requestContent.getRequestParameters().get(RequestParameters.SUBCATEGORY_ID_PARAMETER.getParameterName())[0]);
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.subcategory.id.not.found"));
            }
        }
    }


    public static class Category {

        public static Integer getId(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.CATEGORY_ID_PARAMETER.getParameterName())) {
                return Integer.parseInt(requestContent.getRequestParameters().get(RequestParameters.CATEGORY_ID_PARAMETER.getParameterName())[0]);
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.category.id.not.found"));
            }
        }

        public static String getName(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.CATEGORY_NAME_PARAMETER.getParameterName())) {
                return requestContent.getRequestParameters().get(RequestParameters.CATEGORY_NAME_PARAMETER.getParameterName())[0];
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.category.name.not.found"));
            }
        }
    }

    public static class User {

        public static ProxyUser getUser(SessionRequestContent requestContent) {
            if (requestContent.getSessionAttributes().containsKey(RequestParameters.USER_PARAMETER.getParameterName())) {
                return (ProxyUser) requestContent.getSessionAttributes().get(RequestParameters.USER_PARAMETER.getParameterName());
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.user.not.found"));
            }
        }

        public static String getLogin(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.USER_NAME_PARAMETER.getParameterName())) {
                return requestContent.getRequestParameters().get(RequestParameters.USER_NAME_PARAMETER.getParameterName())[0];
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.user.name.not.found"));
            }
        }

        public static Integer getId(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.USER_ID_PARAMETER.getParameterName())) {
                return Integer.parseInt(requestContent.getRequestParameters().get(RequestParameters.USER_ID_PARAMETER.getParameterName())[0]);
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.user.id.not.found"));
            }
        }
    }
}
