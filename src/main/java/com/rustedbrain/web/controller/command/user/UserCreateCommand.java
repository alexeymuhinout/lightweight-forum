package com.rustedbrain.web.controller.command.user;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.RequestParameters;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

public class UserCreateCommand implements ActionCommand {

    private UserCredentialsLogic logic = UserCredentialsLogic.getInstance();

    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            String name = CommandUtil.User.getName(requestContent);
            String surname = CommandUtil.User.getSurname(requestContent);
            String login = CommandUtil.User.getLogin(requestContent);
            String password = CommandUtil.User.getPassword(requestContent);
            String mail = CommandUtil.User.getMail(requestContent);
            Date birthday = CommandUtil.User.getBirthday(requestContent);
            Integer cityId = getUserCityId(requestContent);
            String cityName = getUserCityName(requestContent);
            if (cityName != null) {
                logic.registerUser(name, surname, login, password, mail, birthday, cityName);
            } else {
                logic.registerUser(name, surname, login, password, mail, birthday, cityId);
            }
            requestContent.getSessionAttributes().put("message", MessageManager.getInstance().getProperty("registration.success"));
            page = ConfigurationManager.getInstance().getProperty("path.page.login");
        } catch (IllegalArgumentException | SQLException | ParseException e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = new UserCreateShowCommand().execute(requestContent);
        }

        return page;
    }

    private Integer getUserCityId(SessionRequestContent requestContent) throws NumberFormatException {
        if (requestContent.getRequestParameters().get(RequestParameters.CITY_ID.getParameterName()) != null
                && requestContent.getRequestParameters().get(RequestParameters.CITY_ID.getParameterName()).length >= 1) {
            return Integer.parseInt(requestContent.getRequestParameters().get(RequestParameters.CITY_ID.getParameterName())[0]);
        } else {
            return null;
        }
    }

    private String getUserCityName(SessionRequestContent requestContent) throws NumberFormatException {
        if (requestContent.getRequestParameters().get(RequestParameters.CITY_NAME.getParameterName()) != null
                && requestContent.getRequestParameters().get(RequestParameters.CITY_NAME.getParameterName()).length >= 1) {
            return requestContent.getRequestParameters().get(RequestParameters.CITY_NAME.getParameterName())[0];
        } else {
            return null;
        }
    }
}
