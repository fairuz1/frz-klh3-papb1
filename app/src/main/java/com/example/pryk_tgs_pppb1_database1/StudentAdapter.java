package com.example.pryk_tgs_pppb1_database1;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        return new MyViewHolder(viewItem, this);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.MyViewHolder holder, int position) {
        final UserData studentData = studentList.get(position);
        holder.username.setText(studentData.getName());
        holder.usersubject.setText(studentData.getSubject());
        holder.useremail.setText(studentData.getEmail());
        holder.usericons.setText(studentData.getImage());
        holder.userid.setText(studentData.getKey());

        if ("avatar_male".equals(holder.usericons.getText().toString())) {
            holder.iconsImage.setBackgroundResource(R.drawable.avatar_male);
        } else if ("avatar_female".equals(holder.usericons.getText().toString())) {
            holder.iconsImage.setBackgroundResource(R.drawable.avatar_female);
        } else {
            holder.iconsImage.setBackgroundResource(R.drawable.avatar_male);
        }
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        ImageButton delete, edit;
        TextView username, usersubject, usericons, useremail, userid;
        CardView itemCard;
        RelativeLayout layout;
        ImageView iconsImage;
        final StudentAdapter adapter;
        public MyViewHolder(@NonNull View itemView, StudentAdapter adapter) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.name);
            usersubject = (TextView) itemView.findViewById(R.id.subject);
            usericons = (TextView) itemView.findViewById(R.id.userIcons);
            useremail = (TextView) itemView.findViewById(R.id.userEmail);
            itemCard = (CardView) itemView.findViewById(R.id.itemCard);
            userid = (TextView) itemView.findViewById(R.id.userId);
            iconsImage = (ImageView) itemView.findViewById(R.id.userIconsImages);

            delete = (ImageButton) itemView.findViewById(R.id.deleteButton);
            edit = (ImageButton) itemView.findViewById(R.id.editButton);

            layout = (RelativeLayout) itemView.findViewById(R.id.layoutData1);
            layout.measure(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            int pagerHeight = layout.getMeasuredHeight();
            iconsImage.setLayoutParams(new RelativeLayout.LayoutParams(pagerHeight, pagerHeight));
            Log.d("layout height", String.valueOf(pagerHeight));

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Log.d("delete tag", "delete button clicked");
                    // Log.d("delete id", userid.getText().toString());

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    View v = LayoutInflater.from(view.getContext()).inflate(R.layout.alert_delete, (RelativeLayout) view.findViewById(R.id.mainAlertLayout));
                    builder.setView(v);

                    final AlertDialog alertDialog = builder.create();

                    if (alertDialog.getWindow() != null){
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    }
                    alertDialog.show();

                    RelativeLayout layoutAlert = (RelativeLayout) v.findViewById(R.id.mainAlertLayout);

                    Button b1 = (Button) layoutAlert.findViewById(R.id.yesDelete);
                    Button b2 = (Button) layoutAlert.findViewById(R.id.noDelete);
                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            db.child("students").child(userid.getText().toString()).removeValue();
                            alertDialog.dismiss();
                            Toast.makeText(view.getContext(), "Data has been deleted", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), EditStudentData.class);
                    intent.putExtra("name", username.getText().toString());
                    intent.putExtra("subject", usersubject.getText().toString());
                    intent.putExtra("icons", usericons.getText().toString());
                    intent.putExtra("mail", useremail.getText().toString());
                    intent.putExtra("id", userid.getText().toString());
                    view.getContext().startActivity(intent);
//                    Log.d("edit tag", "edit button is clicked");
//                    Log.d("edit id", userid.getText().toString());
                }
            });

            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("data key", userid.getText().toString());
        }
    }
}
