package com.example.android.entity;

public class Spirits {
    int _id;
    String _name;
    String _description;
    public Spirits() {}

    public Spirits(int id, String name, String description) {
        this._id = id;
        this._name = name;
        this._description = description;
    }
    public Spirits(String name, String description) {
        this._name = name;
        this._description = description;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }
}
