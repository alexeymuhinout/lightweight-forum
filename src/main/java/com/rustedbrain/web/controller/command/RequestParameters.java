package com.rustedbrain.web.controller.command;

public enum RequestParameters {

    USER_NAME_PARAMETER("user_name"),
    MAIL_PARAMETER("mail"),
    LOGIN_PARAMETER("login"),
    SURNAME_PARAMETER("surname"),
    BIRTHDAY_PARAMETER("birthday"),
    PASSWORD_PARAMETER("password"),
    CITY_ID_PARAMETER("city_id"),
    CITY_NAME_PARAMETER("city_name"),
    CATEGORY_NAME_PARAMETER("category_name"),
    USER_PARAMETER("user"),
    USER_ID_PARAMETER("user_id"),
    CATEGORY_ID_PARAMETER("category_id"),
    SUBCATEGORY_NAME_PARAMETER("subcategory_name"),
    SUBCATEGORY_ID_PARAMETER("subcategory_id"),
    MESSAGE_TEXT_PARAMETER("message_text"),
    MESSAGE_RECEIVER_ID_PARAMETER("reply_to_user_id"),
    MESSAGE_ID_PARAMETER("message_id");

    private final String parameterName;

    RequestParameters(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }
}
