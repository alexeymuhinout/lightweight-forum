package com.rustedbrain.web.controller.command.util;

import com.rustedbrain.web.controller.command.RequestParameters;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;
import com.rustedbrain.web.model.servlet.user.ProxyUser;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CommandUtil {

    public static class Message {
        public static String getText(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.MESSAGE_TEXT.getParameterName())) {
                return requestContent.getRequestParameters().get(RequestParameters.MESSAGE_TEXT.getParameterName())[0];
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.message.text.null"));
            }
        }

        public static Integer getReceiverId(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.MESSAGE_RECEIVER_ID.getParameterName())) {
                return Integer.parseInt(requestContent.getRequestParameters().get(RequestParameters.MESSAGE_RECEIVER_ID.getParameterName())[0]);
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.message.receiver.null"));
            }
        }

        public static Integer getId(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.MESSAGE_ID.getParameterName())) {
                return Integer.parseInt(requestContent.getRequestParameters().get(RequestParameters.MESSAGE_ID.getParameterName())[0]);
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.message.id.not.found"));
            }
        }
    }

    public static class Subcategory {

        public static String getName(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.SUBCATEGORY_NAME.getParameterName())) {
                return requestContent.getRequestParameters().get(RequestParameters.SUBCATEGORY_NAME.getParameterName())[0];
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.subcategory.name.not.found"));
            }
        }

        public static Integer getId(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.SUBCATEGORY_ID.getParameterName())) {
                return Integer.parseInt(requestContent.getRequestParameters().get(RequestParameters.SUBCATEGORY_ID.getParameterName())[0]);
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.subcategory.id.not.found"));
            }
        }
    }


    public static class Category {

        public static Integer getId(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.CATEGORY_ID.getParameterName())) {
                return Integer.parseInt(requestContent.getRequestParameters().get(RequestParameters.CATEGORY_ID.getParameterName())[0]);
            } else if (requestContent.getRequestAttributes().containsKey(RequestParameters.CATEGORY_ID.getParameterName())) {
                return (Integer) requestContent.getRequestAttributes().get(RequestParameters.CATEGORY_ID.getParameterName());
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.category.id.not.found"));
            }
        }

        public static String getName(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.CATEGORY_NAME.getParameterName())) {
                return requestContent.getRequestParameters().get(RequestParameters.CATEGORY_NAME.getParameterName())[0];
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.category.name.not.found"));
            }
        }
    }

    public static class User {

        public static ProxyUser getUser(SessionRequestContent requestContent) {
            if (requestContent.getSessionAttributes().containsKey(RequestParameters.USER.getParameterName())) {
                return (ProxyUser) requestContent.getSessionAttributes().get(RequestParameters.USER.getParameterName());
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.user.not.found"));
            }
        }

        public static String getLogin(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.USER_LOGIN.getParameterName())) {
                return requestContent.getRequestParameters().get(RequestParameters.USER_LOGIN.getParameterName())[0];
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.user.login.not.found"));
            }
        }

        public static Integer getId(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.USER_ID.getParameterName())) {
                return Integer.parseInt(requestContent.getRequestParameters().get(RequestParameters.USER_ID.getParameterName())[0]);
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.user.id.not.found"));
            }
        }

        public static String getName(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.USER_NAME.getParameterName())) {
                return requestContent.getRequestParameters().get(RequestParameters.USER_NAME.getParameterName())[0];
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.user.name.not.found"));
            }
        }

        public static String getSurname(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.USER_SURNAME.getParameterName())) {
                return requestContent.getRequestParameters().get(RequestParameters.USER_SURNAME.getParameterName())[0];
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.user.surname.not.found"));
            }
        }

        public static String getMail(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.USER_MAIL.getParameterName())) {
                return requestContent.getRequestParameters().get(RequestParameters.USER_MAIL.getParameterName())[0];
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.user.mail.not.found"));
            }
        }

        public static Date getBirthday(SessionRequestContent requestContent) throws ParseException {
            if (requestContent.getRequestParameters().get(RequestParameters.USER_BIRTHDAY.getParameterName()) != null
                    && requestContent.getRequestParameters().get(RequestParameters.USER_BIRTHDAY.getParameterName()).length >= 1) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                return new Date(format.parse(requestContent.getRequestParameters().get(RequestParameters.USER_BIRTHDAY.getParameterName())[0]).getTime());
            } else {
                throw new IllegalArgumentException("User birthday cannot be empty");
            }
        }

        public static String getPassword(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.USER_PASSWORD.getParameterName())) {
                return requestContent.getRequestParameters().get(RequestParameters.USER_PASSWORD.getParameterName())[0];
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.user.password.not.found"));
            }
        }

        public static String getAdminToken(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.USER_ADMIN_TOKEN.getParameterName())) {
                return requestContent.getRequestParameters().get(RequestParameters.USER_ADMIN_TOKEN.getParameterName())[0];
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.user.admin-token.not.found"));
            }
        }

        public static Date getBlockUntilDate(SessionRequestContent requestContent) throws ParseException {
            if (requestContent.getRequestParameters().get(RequestParameters.USER_BLOCK_UNTIL_DATE.getParameterName()) != null
                    && requestContent.getRequestParameters().get(RequestParameters.USER_BLOCK_UNTIL_DATE.getParameterName()).length >= 1) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                return new Date(format.parse(requestContent.getRequestParameters().get(RequestParameters.USER_BLOCK_UNTIL_DATE.getParameterName())[0]).getTime());
            } else {
                throw new IllegalArgumentException("User block date cannot be empty");
            }
        }

        public static String getBlockReason(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.USER_BLOCK_REASON.getParameterName())
                    && !requestContent.getRequestParameters().get(RequestParameters.USER_BLOCK_REASON.getParameterName())[0].isEmpty()) {
                return requestContent.getRequestParameters().get(RequestParameters.USER_BLOCK_REASON.getParameterName())[0];
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.user.block.reason.not.found"));
            }
        }
    }

    public static class City {

        public static Integer getId(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.CITY_ID.getParameterName())) {
                return Integer.parseInt(requestContent.getRequestParameters().get(RequestParameters.CITY_ID.getParameterName())[0]);
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.city.id.not.found"));
            }
        }

        public static String getName(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.CITY_NAME.getParameterName())
                    && !requestContent.getRequestParameters().get(RequestParameters.CITY_NAME.getParameterName())[0].isEmpty()) {
                return requestContent.getRequestParameters().get(RequestParameters.CITY_NAME.getParameterName())[0];
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.city.name.not.found"));
            }
        }
    }

    public static class SwearWord {
        public static String getText(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.SWEAR_WORD_TEXT.getParameterName())
                    && !requestContent.getRequestParameters().get(RequestParameters.SWEAR_WORD_TEXT.getParameterName())[0].isEmpty()) {
                return requestContent.getRequestParameters().get(RequestParameters.SWEAR_WORD_TEXT.getParameterName())[0];
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.swear-word.text.not.found"));
            }
        }

        public static Integer getId(SessionRequestContent requestContent) {
            if (requestContent.getRequestParameters().containsKey(RequestParameters.SWEAR_WORD_ID.getParameterName())) {
                return Integer.parseInt(requestContent.getRequestParameters().get(RequestParameters.SWEAR_WORD_ID.getParameterName())[0]);
            } else {
                throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.swear-word.id.not.found"));
            }
        }
    }
}
