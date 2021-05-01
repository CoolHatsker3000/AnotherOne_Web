package com.ngtu.work.dyploma.firebase;

import com.google.firebase.database.*;
import com.ngtu.work.dyploma.views.Manager;
import com.ngtu.work.dyploma.views.Ticket;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class ManagerFirebase {
    private DatabaseReference ref,onChildRef;

    public ManagerFirebase() {
        ref= FirebaseDatabase.getInstance().getReference("managers");
        onChildRef=FirebaseDatabase.getInstance().getReference("tickets");
        DatabaseReference.CompletionListener emptyCompletionListener= new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) { }
        };
        onChildRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Ticket ticket=dataSnapshot.getValue(Ticket.class);
                //Manager manager=getManagerByArea(ticket.adminArea,ticket.locality);
                //manager.ticketIds.add(ticket.id);
                //ref.child(manager.id).child("ticketIds").setValue(manager.ticketIds, emptyCompletionListener);
                //onChildRef.child(ticket.id).child("mid").setValue(manager.id,emptyCompletionListener);
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

    public Manager getManagerByArea(long areaId, long locId){
        final Manager[] result = new Manager[1];
        Query query;
        if (locId==0){
            query=ref.orderByChild("adminArea").equalTo(areaId);
        } else{
            query=ref.orderByChild("locality").equalTo(locId);
        }
        CountDownLatch latch = new CountDownLatch(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                result[0] =dataSnapshot.getValue(Manager.class);
                latch.countDown();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result[0];
    }
}
