package com.rustedbrain.web.controller;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.factory.ActionFactory;
import com.rustedbrain.web.controller.util.SessionRequestContentUtil;
import com.rustedbrain.web.model.SessionRequestContent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private SessionRequestContentUtil requestContentUtil = SessionRequestContentUtil.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {

        // Before all operations
        SessionRequestContent requestContent = requestContentUtil.newSessionRequestContent(req);

        String page = null;

        ActionFactory actionFactory = new ActionFactory();

        ActionCommand command = actionFactory.defineCommand(requestContent);

        page = command.execute(requestContent);

        if (page != null) {

        } else {

        }

        // After all operations
        requestContentUtil.extractValuesToRequest(req, requestContent);
    }

}
