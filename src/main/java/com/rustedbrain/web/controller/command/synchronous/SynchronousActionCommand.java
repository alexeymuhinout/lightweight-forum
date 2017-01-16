package com.rustedbrain.web.controller.command.synchronous;

import com.rustedbrain.web.controller.command.factory.ActionCommand;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

public interface SynchronousActionCommand extends ActionCommand {
    String execute(SessionRequestContent requestContent);
}
