package com.rustedbrain.web.controller.command.user;

import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.UserCredentialsLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;
import com.rustedbrain.web.model.servlet.user.PreviewUser;

public class UserPreviewShowCommand implements com.rustedbrain.web.controller.command.ActionCommand {

    private UserCredentialsLogic logic = UserCredentialsLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            String userLogin = CommandUtil.User.getLogin(requestContent);
            PreviewUser user = logic.getPreviewUser(userLogin);
            requestContent.getRequestAttributes().put("user_preview", user);
            page = ConfigurationManager.getInstance().getProperty("path.page.user.preview");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = ConfigurationManager.getInstance().getProperty("path.page.error");
        }

        return page;
    }
}
