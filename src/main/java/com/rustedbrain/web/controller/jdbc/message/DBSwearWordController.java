package com.rustedbrain.web.controller.jdbc.message;

import com.rustedbrain.web.controller.jdbc.DBController;
import com.rustedbrain.web.model.jdbc.SwearWord;

import java.sql.SQLException;

public abstract class DBSwearWordController extends DBController<SwearWord> {

    public abstract boolean isSwearWord(String word) throws SQLException;

}
