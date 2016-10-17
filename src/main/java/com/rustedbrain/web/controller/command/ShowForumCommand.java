package com.rustedbrain.web.controller.command;

import com.rustedbrain.web.controller.logic.ForumLogic;
import com.rustedbrain.web.model.Category;
import com.rustedbrain.web.model.SessionRequestContent;

import java.util.List;

/**
 * Created by Bloodar on 04.10.2016.
 */
public class ShowForumCommand implements ActionCommand {

    private ForumLogic logic = ForumLogic.getInstance();

    public String execute(SessionRequestContent requestContent) {

        String page = null;

        String currentCategoriesPage = requestContent.getRequestParameters().get("current_categories_page")[0];
        String maxCategoriesPerPage = requestContent.getRequestParameters().get("max_categories_per_page")[0];

        try {
            int currentPage = Integer.parseInt(currentCategoriesPage);
            int maxPages = Integer.parseInt(maxCategoriesPerPage);
            List<Category> categories = logic.getCategories(currentPage, maxPages);
        } catch (IllegalArgumentException e) {

        }


        return page;
    }
}
