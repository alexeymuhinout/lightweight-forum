package com.rustedbrain.web.controller.command.user;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.jdbc.City;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

import java.util.List;

public class UserUpdateShowCommand implements ActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();
    private UserCredentialsLogic userCredentialsLogic = UserCredentialsLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            List<City> cities = userCredentialsLogic.getAllCities();
            requestContent.getRequestAttributes().put("cities", cities);
            page = ConfigurationManager.getInstance().getProperty("path.page.profile.update");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getSessionAttributes().put("error", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.error");
        }
        return page;
    }

}
