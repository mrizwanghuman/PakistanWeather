package com.example.admin.pakistanweather.util;

/**
 * Created by  Admin on 12/13/2017.
 */

public interface BasePresenter<V extends BaseView> {
    void onAttachView(V view);
    void onDetachView();
}
