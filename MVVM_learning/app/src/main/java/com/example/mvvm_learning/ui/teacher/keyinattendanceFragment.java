package com.example.mvvm_learning.ui.teacher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_learning.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class keyinattendanceFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        return inflater.inflate(R.layout.fragment_teacher_input_attendance, null);
    }

    //change from select all to select absent only.
    //change in api logic to auto-select present to class.


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //you can set the title for your toolbar here for different fragments different titles
        // add homework (yes no selection option)
        // re-edit the API logic for keying in attendance

        getActivity().setTitle("KEY IN ATTENDANCE");
    }
}