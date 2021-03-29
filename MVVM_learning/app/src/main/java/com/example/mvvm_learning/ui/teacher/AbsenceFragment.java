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

//    public void get_teacher_data()
//    {
//        Call<teacher_response> call = RetrofitClient.getmInstance().getApi().getTeacher_Class_info(login_id);
//        call.enqueue(new Callback<teacher_response>() {
//            @Override
//            public void onResponse(Call<teacher_response> call, Response<teacher_response> response) {
//                if(response.isSuccessful())
//                {
//                    temp1 = response.body().getMessage().get(0).getT_name();
//                    temp2 = response.body().getMessage().get(0).getC_name();
//                    temp3 = response.body().getMessage().get(0).getForm();
//                    class_id = response.body().getMessage().get(0).getC_id();
//                    get_absence_data(class_id);
//
//                }else
//                {
//                    Toast.makeText(getContext().getApplicationContext(), "kathai", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<teacher_response> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void get_absence_data(String class_id)
//    {
//
//        Call<teacher_absence_response> call2 = RetrofitClient.getmInstance().getApi().getTeacher_absent_student(class_id,"2020-10-01");
//        call2.enqueue(new Callback<teacher_absence_response>() {
//            @Override
//            public void onResponse(Call<teacher_absence_response> call, Response<teacher_absence_response> response) {
//
//               ta = new String[response.body().getMessage().size()];
//
//               if(response.isSuccessful())
//               {
//                   count = response.body().getMessage().size();
//
//                   for(int x = 0 ; x < count ; x++)
//                   {
//                       ta[x] = response.body().getMessage().get(x).getS_id();
//                       new_row = new TableRow(getContext().getApplicationContext());
//                       new_row.setPadding(5,5,5,5);
//
//                       id = new TextView(getContext().getApplicationContext());
//                       id.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
//                       name = new TextView(getContext().getApplicationContext());
//                       name.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 3.0f));
//
//                       id.setId(x);
//                       id.setText(String.format("%s", (x+1)));
//                       id.setWidth(100);
//                       id.setTextColor(Color.BLACK);
//                       name.setText(response.body().getMessage().get(x).getS_id());
//                       name.setTextColor(Color.BLACK);
//
//                       new_row.addView(id);
//                       new_row.addView(name);
//                       new_row.setGravity(Gravity.CENTER);
//                       new_row.setBackgroundColor(Color.rgb(250,128,114));
//
//                       new_row.setLayoutParams(new TableLayout.LayoutParams(
//                               TableLayout.LayoutParams.WRAP_CONTENT,
//                               TableLayout.LayoutParams.WRAP_CONTENT, 1.0f));
//
//                       find_table.addView(new_row);
//
//                   }
//
//                    select_s.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                            builder.setTitle("Choose Student");
//                            builder.setItems(ta, new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int item) {
//                                    temp_student_id = ta[item];
//                                    getStudentDetails(temp_student_id);
//                                }
//                            });
//                            AlertDialog alert = builder.create();
//                            alert.show();
//                        }
//                    });
//
//
//
//               }else
//               {
//                   Toast.makeText(getContext().getApplicationContext(), "kathai", Toast.LENGTH_SHORT).show();
//               }
//            }
//
//            @Override
//            public void onFailure(Call<teacher_absence_response> call, Throwable t) {
//
//            }
//        });
//
//    }
//
//    public void getStudentDetails(String student_user_id)
//    {
//        temp_parent_num = new String();
//        parent_email = new String();
//        parent_name = new String();
//
//        Call<teacher_contact_response> call_parent = RetrofitClient.getmInstance().getApi().getParentNumByStudentId(student_user_id);
//        call_parent.enqueue(new Callback<teacher_contact_response>() {
//            @Override
//            public void onResponse(Call<teacher_contact_response> call, Response<teacher_contact_response> response) {
//                temp_parent_num = response.body().getMessage().get(0).getPhone();
//                parent_email = response.body().getMessage().get(0).getEmail();
//                parent_name = response.body().getMessage().get(0).getName();
//
//                msg_to_parents = "This Message is to notify your Child is Absent today.";
//                contact.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                        builder.setTitle("Contact:");
//                        builder.setItems(contact_type, new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int item) {
//                                switch (item)
//                                {
//                                    case 0:
//
//                                        Toast.makeText(getContext().getApplicationContext(), temp_parent_num, Toast.LENGTH_SHORT).show();
//                                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
//                                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        callIntent.setData(Uri.parse("tel:" + "+60"+ temp_parent_num));
//                                        startActivity(callIntent);
//                                        break;
//
//                                    case 1:
//
//                                        Toast.makeText(getContext().getApplicationContext(), temp_parent_num, Toast.LENGTH_SHORT).show();
//
//                                        boolean installed = appInstalledOrNot("com.whatsapp");
//                                        if (installed){
//                                            Intent intent = new Intent(Intent.ACTION_VIEW);
//                                            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+60"+temp_parent_num + "&text="+msg_to_parents));
//                                            startActivity(intent);
//                                        }else {
//                                            Toast.makeText(getContext().getApplicationContext(), "Whats app not installed on your device", Toast.LENGTH_SHORT).show();
//                                        }
//
//                                        break;
//
//                                    case 2:
//
//                                        Toast.makeText(getContext().getApplicationContext(), temp_parent_num, Toast.LENGTH_SHORT).show();
//                                        Intent email = new Intent(Intent.ACTION_SEND);
//                                        email.putExtra(Intent.EXTRA_EMAIL, new String[]{parent_email});
//                                        email.putExtra(Intent.EXTRA_SUBJECT, "Student Attendance");
//                                        email.putExtra(Intent.EXTRA_TEXT, "Mr/Mrs "+ parent_name + "This email is to keep you updated with child's attendance");
//
//                                        //need this to prompts email client only
//                                        email.setType("message/rfc822");
//
//                                        startActivity(Intent.createChooser(email, "Choose an Email client :"));
//
//
//                                        break;
//                                }
//                            }
//                        });
//                        AlertDialog alert = builder.create();
//                        alert.show();
//                    }
//                });
//
//            }
//
//            @Override
//            public void onFailure(Call<teacher_contact_response> call, Throwable t) {
//
//            }
//        });
//
//    };
//
//    private boolean appInstalledOrNot(String url){
//        PackageManager packageManager = getContext().getApplicationContext().getPackageManager();
//        boolean app_installed;
//        try {
//            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
//            app_installed = true;
//        }catch (PackageManager.NameNotFoundException e){
//            app_installed = false;
//        }
//        return app_installed;
//    }
//
}