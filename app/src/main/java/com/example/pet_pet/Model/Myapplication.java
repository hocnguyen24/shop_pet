package com.example.pet_pet.Model;

import android.app.Application;

import androidx.lifecycle.ViewModelStore;

public class Myapplication extends Application {
    private ViewModelStore viewModelStore;

    @Override
    public void onCreate() {
        super.onCreate();
        viewModelStore = new ViewModelStore();
    }

    public ViewModelStore getViewModelStore() {
        return viewModelStore;
    }
}
