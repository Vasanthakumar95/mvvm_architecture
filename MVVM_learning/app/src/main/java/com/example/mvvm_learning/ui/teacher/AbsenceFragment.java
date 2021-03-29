package com.example.mvvm_learning.ui.teacher;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvm_learning.R;
import com.example.mvvm_learning.adapters.teacher.lv_absence;
import com.example.mvvm_learning.adapters.teacher.lv_attendance;
import com.example.mvvm_learning.databinding.FragmentTeacherAbsenceBinding;
import com.example.mvvm_learning.databinding.FragmentTeacherAttendanceBinding;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_absence;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_get_class_att;
import com.example.mvvm_learning.viewModels.teacher.AbsenceViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbsenceFragment extends Fragment {

    /// change table top br color to red
    /// change table gravity same as attend fragment's table
    /// select student and button contact bring down to bottom (following the student list)
    /// change side navigation bar replace Attendance with Today's Attendance. (same with absent).


//    String login_id = login.getCurrentUser();

//    String[] contact_type = new String[]{"Phone", "Whatsapp", "E-mail"};
//    public String temp1,temp2,temp3, class_id, temp_student_id;
//    String temp_parent_num, parent_num_selected, msg_to_parents;
//    String parent_email, parent_name;
//    int count;
//    TableLayout find_table;
//    TableRow new_row;
//
//
//    Button select_s, contact;
//
//    TextView id;
//    TextView name;
//
//    String[] ta;

    private ListView listView;

    AbsenceViewModel mAbsenceViewModel = new AbsenceViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mAbsenceViewModel = new ViewModelProvider(requireActivity()).get(AbsenceViewModel.class);
        FragmentTeacherAbsenceBinding binding = FragmentTeacherAbsenceBinding.inflate(inflater, container, false);

        //set binding variables here
        binding.setLifecycleOwner(this);
        binding.setTeacherAbsenceViewModel(mAbsenceViewModel);
        return binding.getRoot();
    }

    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("CLASS ABSENCE");

        mAbsenceViewModel.getAbb_list().observe(getViewLifecycleOwner(), new Observer<List<teacher_absence>>() {
            @Override
            public void onChanged(List<teacher_absence> teacher_absence) {

                listView = view.findViewById(R.id.lv_absence);

                //creating the adapter
                lv_absence adapter = new lv_absence(view.getContext(), R.layout.lv_teacher_absence, teacher_absence);

                //attaching adapter to the listview
                listView.setAdapter(adapter);


            }
        });

    }
}
