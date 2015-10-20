package com.mobiletourismapp.activities;

public interface ResponseHandler {
void onSuccess(String list);
void onFailure(String error);
}
