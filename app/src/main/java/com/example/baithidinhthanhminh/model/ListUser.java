package com.example.baithidinhthanhminh.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListUser {
    @SerializedName("page")
    private int page;
    @SerializedName("data")
    private List<User> listUser;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }
}
