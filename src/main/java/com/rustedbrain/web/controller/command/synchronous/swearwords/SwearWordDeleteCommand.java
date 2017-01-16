package com.rustedbrain.web.controller.command.synchronous.swearwords;

import com.rustedbrain.web.controller.command.synchronous.SynchronousActionCommand;
import com.rustedbrain.web.controller.command.util.CommandUtil;
import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public class SwearWordDeleteCommand implements SynchronousActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    @Override
    public String execute(SessionRequestContent requestContent) {

        try {
            logic.deleteSwearWord(CommandUtil.SwearWord.getId(requestContent));
            requestContent.getRequestAttributes().put("message", MessageManager.getInstance().getProperty("swear-word.delete.success"));
        } catch (Exception e) {
            e.printStackTrace();
            requestContent.getRequestAttributes().put("error", e.getMessage());
        }
        return new SwearWordsShowCommand().execute(requestContent);
    }
}
