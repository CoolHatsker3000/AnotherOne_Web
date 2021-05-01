package com.ngtu.work.dyploma.firebase;

import com.google.firebase.database.*;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class TestData {
    private DatabaseReference ref;
    private List<String> strings;

    public TestData() {
        ref= FirebaseDatabase.getInstance().getReference("testValues");
        strings=new LinkedList<>();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!strings.isEmpty()) strings.clear();
                for (DataSnapshot dt: dataSnapshot.getChildren()){
                    strings.add(dt.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public List<String> getStrings() {
        return strings;
    }
}
