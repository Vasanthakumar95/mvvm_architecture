package com.example.mvvm_learning.adapters.teacher;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvvm_learning.R;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_get_class_att;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import androidx.annotation.NonNull;

//we need to extend the ArrayAdapter class as we are building an adapter
public class lv_attendance extends ArrayAdapter<teacher_get_class_att> {

    //the list values in the List of type hero
    List<teacher_get_class_att> attList;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public lv_attendance(@NonNull Context context, int resource, List<teacher_get_class_att> attList) {
        super(context, resource, attList);
        this.attList = attList;
        this.context = context;
        this.resource = resource;
    }


    //this will return the ListView Item as a View
    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        ImageView imgview = view.findViewById(R.id.img_student);
        TextView textViewName = view.findViewById(R.id.att_student_Name);
        TextView textViewTime = view.findViewById(R.id.att_Student_time);
        TextView textView_ = view.findViewById(R.id.att_Student_);

        //getting the hero of the specified position
        teacher_get_class_att att = attList.get(position);

        //adding values to the list item
        imgview.setImageResource(R.drawable.ic_round_account_circle_24);
        textViewName.setText(att.getS_name());
        textViewTime.setText("Check In Time: " + att.getA_time());
        textView_.setText(att.getS_id());

        //finally returning the view
        return view;
    }
}