package com.rustedbrain.web.controller.command.synchronous.user;

import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.jdbc.City;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

import java.sql.SQLException;
import java.util.List;


public class UserCreateShowCommand implements SynchronousActionCommand {

    private UserCredentialsLogic logic = UserCredentialsLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            List<City> cities = logic.getAllCities();
            requestContent.getRequestAttributes().put("cities", cities);
            page = ConfigurationManager.getInstance().getProperty("path.page.user.create");
        } catch (SQLException e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.user.create");
        }
        return page;
    }
}
