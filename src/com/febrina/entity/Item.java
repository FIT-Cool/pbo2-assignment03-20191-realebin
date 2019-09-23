package com.febrina.entity;

import java.util.Date;

/**
 * Class Item
 * @author Febrina 1772006
 */
public class Item {
    public String _id;
    public String _name;
    public String _expiredDate;
    public Category _category;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_expiredDate() {
        return _expiredDate;
    }

    public void set_expiredDate(String _expiredDate) {
        this._expiredDate = _expiredDate;
    }

    public Category get_category() {
        return _category;
    }

    public void set_category(Category _category) {
        this._category = _category;
    }
}

