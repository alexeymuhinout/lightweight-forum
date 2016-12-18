package com.rustedbrain.web.controller.command.user;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

import java.sql.Date;

public class UserUpdateCommand implements ActionCommand {

    private UserCredentialsLogic logic = UserCredentialsLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            Integer userId = CommandUtil.User.getId(requestContent);
            String name = CommandUtil.User.getName(requestContent);
            String surname = CommandUtil.User.getSurname(requestContent);
            String login = CommandUtil.User.getLogin(requestContent);
            String mail = CommandUtil.User.getMail(requestContent);
            Date birthday = CommandUtil.User.getBirthday(requestContent);
            try {
                String cityName = CommandUtil.City.getName(requestContent);
                logic.updateUserCredentials(userId, name, surname, login, mail, birthday, cityName);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                Integer cityId = CommandUtil.City.getId(requestContent);
                logic.updateUserCredentials(userId, name, surname, login, mail, birthday, cityId);
            }
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("profile.update.success"));
            page = new UserLogoutCommand().execute(requestContent);
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = new UserUpdateShowCommand().execute(requestContent);
        }
        return page;
    }
}
