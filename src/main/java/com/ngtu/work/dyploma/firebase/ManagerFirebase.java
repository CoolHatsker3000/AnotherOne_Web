package com.ngtu.work.dyploma.firebase;

import com.google.firebase.database.*;
import com.ngtu.work.dyploma.views.Manager;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class ManagerFirebase {
    private DatabaseReference ref,onChildRef;

    public ManagerFirebase() {
        ref= FirebaseDatabase.getInstance().getReference("managers");
        onChildRef=FirebaseDatabase.getInstance().getReference("tickets");
        onChildRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                return;
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addManager(Manager manager) throws InterruptedException {
        DatabaseReference toPush=ref.push();
        manager.id=toPush.getKey();
        CountDownLatch latch = new CountDownLatch(1);
        toPush.setValue(manager, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                latch.countDown();
            }
        });
        latch.await();
    }
}
