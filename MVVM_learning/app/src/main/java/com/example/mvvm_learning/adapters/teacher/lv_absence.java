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
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_absence;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import androidx.annotation.NonNull;

//we need to extend the ArrayAdapter class as we are building an adapter
public class lv_absence extends ArrayAdapter<teacher_absence> {

    //the list values in the List of type hero
    List<teacher_absence> abList;

    //activity context
    Context context;

    //the layout resource file for the list items
    int resource;

    //constructor initializing the values
    public lv_absence(@NonNull Context context, int resource, List<teacher_absence> abList) {
        super(context, resource, abList);
        this.abList = abList;
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
        ImageView imgview = view.findViewById(R.id.img_student_ab);
        TextView textViewName = view.findViewById(R.id.ab_student_Name);
        TextView textViewTime = view.findViewById(R.id.ab_Student_id);
        TextView textView_ = view.findViewById(R.id.ab_Student_parent);

        //getting the hero of the specified position
        teacher_absence ab = abList.get(position);

        //adding values to the list item
        imgview.setImageResource(R.drawable.ic_round_account_circle_24);
        textViewName.setText(ab.getS_name());
        textViewTime.setText(ab.getS_id());
        textView_.setText("Parent ID: " + ab.getP_id());

        //finally returning the view
        return view;
    }
}