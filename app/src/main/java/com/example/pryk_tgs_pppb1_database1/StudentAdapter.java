package com.example.pryk_tgs_pppb1_database1;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    private List<UserData> studentList;
    private Activity activity;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    public StudentAdapter (List<UserData>studentList, Activity activity) {
        this.studentList = studentList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from((parent.getContext()));
        View viewItem = inflater.inflate(R.layout.layout_items, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.MyViewHolder holder, int position) {
        final UserData studentData = studentList.get(position);
        holder.username.setText(studentData.getName());
        holder.usersubject.setText(studentData.getSubject());
        holder.useremail.setText(studentData.getEmail());
        holder.usericons.setText(studentData.getImage());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username, usersubject, usericons, useremail;
        CardView itemCard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.name);
            usersubject = (TextView) itemView.findViewById(R.id.subject);
            usericons = (TextView) itemView.findViewById(R.id.userIcons);
            useremail = (TextView) itemView.findViewById(R.id.userEmail);
            itemCard = (CardView) itemView.findViewById(R.id.itemCard);
        }
    }
}
