package com.example.mvvm_learning.ui.teacher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mvvm_learning.R;
import com.example.mvvm_learning.adapters.teacher.lv_attendance;
import com.example.mvvm_learning.databinding.FragmentTeacherAttendanceBinding;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_get_class_att;
import com.example.mvvm_learning.viewModels.teacher.AttendanceViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


public class AttendanceFragment extends Fragment {


    //the listview
    private ListView listView;

    AttendanceViewModel mAttendanceViewModel = new AttendanceViewModel();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAttendanceViewModel = new ViewModelProvider(requireActivity()).get(AttendanceViewModel.class);
        FragmentTeacherAttendanceBinding binding = FragmentTeacherAttendanceBinding.inflate(inflater, container, false);
        //set binding variables here
        binding.setLifecycleOwner(this);
        binding.setTeacherAttendanceViewModel(mAttendanceViewModel);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("CLASS ATTENDANCE");

        //
        mAttendanceViewModel.getAtt_list().observe(getViewLifecycleOwner(), new Observer<List<teacher_get_class_att>>() {

            @Override
            public void onChanged(List<teacher_get_class_att> teacher_get_class_atts) {

                listView = view.findViewById(R.id.lv_attendance);

                //creating the adapter
                lv_attendance adapter = new lv_attendance(view.getContext(), R.layout.lv_teacher_attendance, teacher_get_class_atts);

                //attaching adapter to the listview
                listView.setAdapter(adapter);
            }
        });
    }

}