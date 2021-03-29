package com.example.mvvm_learning.ui.teacher;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mvvm_learning.R;
import com.example.mvvm_learning.viewModels.teacher.HomeViewModel;
import com.example.mvvm_learning.databinding.FragmentTeacherHomeBinding;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
//import de.hdodenhof.circleimageview.CircleImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


public class HomeFragment extends Fragment {
    private FragmentTeacherHomeBinding mfragmentTeacherHomeBinding;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // change attendance data : total = ?? (above attended total student)
        /// seperate attend and absent button top and bottom
        /// Today's Attendance change to Today's Attendance Summary.
        HomeViewModel mHomeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        FragmentTeacherHomeBinding binding = FragmentTeacherHomeBinding.inflate(inflater, container, false);
        //set binding variables here
        binding.setLifecycleOwner(this);
        binding.setTeacherHomeViewModel(mHomeViewModel);
        return binding.getRoot();
    }
//
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("HOME");

    }

}