package com.rustedbrain.web.controller;

import com.rustedbrain.web.controller.command.ActionCommand;
import com.rustedbrain.web.controller.command.factory.ActionFactory;
import com.rustedbrain.web.controller.resource.ConfigurationManager;
import com.rustedbrain.web.controller.resource.MessageManager;
import com.rustedbrain.web.controller.util.SessionRequestContentUtil;
import com.rustedbrain.web.model.servlet.SessionRequestContent;

import javax.servlet.RequestDispatcher;
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
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Before all operations
        SessionRequestContent requestContent = requestContentUtil.newSessionRequestContent(req);

        ActionFactory actionFactory = new ActionFactory();

        ActionCommand command = actionFactory.defineCommand(requestContent);

        String page = command.execute(requestContent);

        // After all operations
        requestContentUtil.extractValuesToRequest(req, requestContent);

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        } else {
            page = ConfigurationManager.getInstance().getProperty("path.page.index");
            req.getSession().setAttribute("nullPage",
                    MessageManager.getInstance().getProperty("message.nullpage"));
            resp.sendRedirect(req.getContextPath() + page);
        }
    }

}
