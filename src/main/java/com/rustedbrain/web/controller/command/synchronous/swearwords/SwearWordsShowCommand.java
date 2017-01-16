package com.rustedbrain.web.controller.command.synchronous.swearwords;

import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.command.synchronous.user.UserShowCommand;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.model.jdbc.SwearWord;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

import java.util.List;

public class SwearWordsShowCommand implements SynchronousActionCommand {

    private ForumLogic forumLogic = ForumLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            List<SwearWord> swearWords = forumLogic.getSwearWords();
            requestContent.getRequestAttributes().put("swear_words", swearWords);
            page = ConfigurationManager.getInstance().getProperty("path.page.swear-words");
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = new UserShowCommand().execute(requestContent);
        }

        return page;
    }
}
