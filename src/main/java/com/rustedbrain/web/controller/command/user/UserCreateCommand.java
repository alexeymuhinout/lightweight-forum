package com.rustedbrain.web.controller.command.user;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.RequestParameters;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

import java.sql.Date;

public class UserCreateCommand implements ActionCommand {

    private UserCredentialsLogic logic = UserCredentialsLogic.getInstance();

    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            String name = CommandUtil.User.getName(requestContent).trim();
            String surname = CommandUtil.User.getSurname(requestContent).trim();
            String login = CommandUtil.User.getLogin(requestContent).trim();
            String password = CommandUtil.User.getPassword(requestContent).trim();
            String mail = CommandUtil.User.getMail(requestContent).trim();
            Date birthday = CommandUtil.User.getBirthday(requestContent);
            Integer cityId = getUserCityId(requestContent);
            String cityName = getUserCityName(requestContent);
            String userAdminToken = null;
            try {
                userAdminToken = CommandUtil.User.getAdminToken(requestContent);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            if (cityName != null) {
                logic.registerUser(name, surname, login, password, mail, birthday, cityName, userAdminToken);
            } else {
                logic.registerUser(name, surname, login, password, mail, birthday, cityId, userAdminToken);
            }
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("registration.success"));
            page = ConfigurationManager.getInstance().getProperty("path.page.user.login");
        } catch (Exception e) {
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
                && requestContent.getRequestParameters().get(RequestParameters.CITY_NAME.getParameterName()).length >= 1
                && !requestContent.getRequestParameters().get(RequestParameters.CITY_NAME.getParameterName())[0].isEmpty()) {
            return requestContent.getRequestParameters().get(RequestParameters.CITY_NAME.getParameterName())[0].trim();
        } else {
            return null;
        }
    }
}
