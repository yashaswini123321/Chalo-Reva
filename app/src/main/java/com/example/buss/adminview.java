package com.example.buss;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class adminview extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Individual_user> userArrayList;
    MyAdapter myAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminview);



        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching data......");
        progressDialog.show();

        recyclerView=findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db=FirebaseFirestore.getInstance();
        userArrayList=new ArrayList<Individual_user>();
        myAdapter=new MyAdapter(adminview.this,userArrayList);

        recyclerView.setAdapter(myAdapter);

        EventChangeListener();

    }

    private void EventChangeListener() {

        db.collection("users").orderBy("name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(error!=null){

                            if(progressDialog.isShowing())
                                progressDialog.dismiss();

                            Log.e("Firestore error",error.getMessage());
                            return;
                        }
                        for(DocumentChange dc: value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                userArrayList.add(dc.getDocument().toObject(Individual_user.class));
                            }

                            myAdapter.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();


                        }
                    }
                });
    }
}