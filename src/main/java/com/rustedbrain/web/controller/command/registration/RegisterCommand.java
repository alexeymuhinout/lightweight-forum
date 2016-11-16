package com.rustedbrain.web.controller.command.registration;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.RequestParameters;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RegisterCommand implements ActionCommand {

    private UserCredentialsLogic logic = UserCredentialsLogic.getInstance();

    public String execute(SessionRequestContent requestContent) {

        String page = null;

        try {
            String name = getUserName(requestContent);
            String surname = getUserSurname(requestContent);
            String login = getUserLogin(requestContent);
            String password = getUserPassword(requestContent);
            String mail = getUserMail(requestContent);
            Date birthday = getUserBirthday(requestContent);
            Integer cityId = getUserCityId(requestContent);
            String cityName = getUserCityName(requestContent);
            if (cityId != null) {
                logic.registerUser(name, surname, login, password, mail, birthday, cityId);
            } else {
                logic.registerUser(name, surname, login, password, mail, birthday, cityName);
            }
            requestContent.getSessionAttributes().put("message", MessageManager.getInstance().getProperty("registration.success"));
            page = ConfigurationManager.getInstance().getProperty("path.page.login");
        } catch (IllegalArgumentException | SQLException | ParseException e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.registration");
        }


        return page;
    }

    private Integer getUserCityId(SessionRequestContent requestContent) throws NumberFormatException {
        if (requestContent.getRequestParameters().get(RequestParameters.CITY_ID_PARAMETER.getParameterName()) != null
                && requestContent.getRequestParameters().get(RequestParameters.CITY_ID_PARAMETER.getParameterName()).length >= 1) {
            return Integer.parseInt(requestContent.getRequestParameters().get(RequestParameters.CITY_ID_PARAMETER.getParameterName())[0]);
        } else {
            return null;
        }
    }

    private String getUserCityName(SessionRequestContent requestContent) throws NumberFormatException {
        if (requestContent.getRequestParameters().get(RequestParameters.CITY_NAME_PARAMETER.getParameterName()) != null
                && requestContent.getRequestParameters().get(RequestParameters.CITY_NAME_PARAMETER.getParameterName()).length >= 1) {
            return requestContent.getRequestParameters().get(RequestParameters.CITY_NAME_PARAMETER.getParameterName())[0];
        } else {
            return null;
        }
    }

    private Date getUserBirthday(SessionRequestContent requestContent) throws ParseException {
        if (requestContent.getRequestParameters().get(RequestParameters.BIRTHDAY_PARAMETER.getParameterName()) != null
                && requestContent.getRequestParameters().get(RequestParameters.BIRTHDAY_PARAMETER.getParameterName()).length >= 1) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return new Date(format.parse(requestContent.getRequestParameters().get(RequestParameters.BIRTHDAY_PARAMETER.getParameterName())[0]).getTime());
        } else {
            throw new IllegalArgumentException("User birthday cannot be empty");
        }
    }

    private String getUserMail(SessionRequestContent requestContent) {
        if (requestContent.getRequestParameters().get(RequestParameters.MAIL_PARAMETER.getParameterName()) != null
                && requestContent.getRequestParameters().get(RequestParameters.MAIL_PARAMETER.getParameterName()).length >= 1) {
            return requestContent.getRequestParameters().get(RequestParameters.MAIL_PARAMETER.getParameterName())[0];
        } else {
            throw new IllegalArgumentException("User mail cannot be empty");
        }
    }

    private String getUserPassword(SessionRequestContent requestContent) {
        if (requestContent.getRequestParameters().get(RequestParameters.PASSWORD_PARAMETER.getParameterName()) != null
                && requestContent.getRequestParameters().get(RequestParameters.PASSWORD_PARAMETER.getParameterName()).length >= 1) {
            return requestContent.getRequestParameters().get(RequestParameters.PASSWORD_PARAMETER.getParameterName())[0];
        } else {
            throw new IllegalArgumentException("User password cannot be empty");
        }
    }

    private String getUserLogin(SessionRequestContent requestContent) {
        if (requestContent.getRequestParameters().get(RequestParameters.LOGIN_PARAMETER.getParameterName()) != null
                && requestContent.getRequestParameters().get(RequestParameters.LOGIN_PARAMETER.getParameterName()).length >= 1) {
            return requestContent.getRequestParameters().get(RequestParameters.LOGIN_PARAMETER.getParameterName())[0];
        } else {
            throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.user.login.empty"));
        }
    }

    private String getUserSurname(SessionRequestContent requestContent) {
        if (requestContent.getRequestParameters().get(RequestParameters.SURNAME_PARAMETER.getParameterName()) != null
                && requestContent.getRequestParameters().get(RequestParameters.SURNAME_PARAMETER.getParameterName()).length >= 1) {
            return requestContent.getRequestParameters().get(RequestParameters.SURNAME_PARAMETER.getParameterName())[0];
        } else {
            throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.user.surname.empty"));
        }
    }

    private String getUserName(SessionRequestContent requestContent) {
        if (requestContent.getRequestParameters().get(RequestParameters.USER_NAME_PARAMETER.getParameterName()) != null
                && requestContent.getRequestParameters().get(RequestParameters.USER_NAME_PARAMETER.getParameterName()).length >= 1) {
            return requestContent.getRequestParameters().get(RequestParameters.USER_NAME_PARAMETER.getParameterName())[0];
        } else {
            throw new IllegalArgumentException(MessageManager.getInstance().getProperty("request.user.name.empty"));
        }
    }


}
