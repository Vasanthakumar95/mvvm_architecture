package com.example.mvvm_learning.viewModels.teacher;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class keyinattendanceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public keyinattendanceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}