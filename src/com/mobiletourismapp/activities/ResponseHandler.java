package com.mobiletourismapp.activities;

public interface ResponseHandler {
void onSuccess(Tourism list);
void onFailure(String error);
}
