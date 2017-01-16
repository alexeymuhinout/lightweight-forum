package com.rustedbrain.web.controller.command.synchronous.swearwords;

import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class SwearWordCreateCommand implements SynchronousActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    public String execute(SessionRequestContent requestContent) {

        String page;

        try {
            String text = CommandUtil.SwearWord.getText(requestContent).trim();
            logic.createSwearWord(text);
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("message.creation.success"));
            page = new SwearWordsShowCommand().execute(requestContent);
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
            page = new SwearWordsShowCommand().execute(requestContent);
        }

        return page;
    }
}
