package com.rustedbrain.web.controller.command;

public enum RequestParameters {

    USER("user"),
    USER_ID("user_id"),
    USER_NAME("user_name"),
    USER_MAIL("user_mail"),
    USER_LOGIN("user_login"),
    USER_SURNAME("user_surname"),
    USER_BIRTHDAY("user_birthday"),
    USER_PASSWORD("user_password"),
    USER_REGISTRATION("user_registration"),
    CITY_ID("city_id"),
    CITY_NAME("city_name"),
    CATEGORY_NAME("category_name"),
    CATEGORY_ID("category_id"),
    SUBCATEGORY("subcategory"),
    SUBCATEGORY_NAME("subcategory_name"),
    SUBCATEGORY_ID("subcategory_id"),
    MESSAGE_TEXT("message_text"),
    MESSAGE_RECEIVER_ID("reply_to_user_id"),
    MESSAGE_ID("message_id");

    private final String parameterName;

    RequestParameters(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterName() {
        return parameterName;
    }
}
