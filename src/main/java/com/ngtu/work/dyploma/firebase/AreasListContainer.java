package com.ngtu.work.dyploma.firebase;

import com.google.firebase.database.*;
import com.ngtu.work.dyploma.views.Place;
import com.ngtu.work.dyploma.views.Ticket;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class AreasListContainer {
    private DatabaseReference areasRef,locRef;
    /*private DataSnapshot areaDs;
    private ValueEventListener listener;*/

    public AreasListContainer() {
        areasRef= FirebaseDatabase.getInstance().getReference("adminAreas");
        locRef=FirebaseDatabase.getInstance().getReference("localities");

        /*listener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                areaDs=dataSnapshot;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };*/
        //areasRef.addValueEventListener(listener);
    }

    public List<Place> getAreasList() throws InterruptedException {
        List<Place> areas=new LinkedList<>();
        CountDownLatch latch = new CountDownLatch(1);
        areasRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    areas.add(new Place(child.getValue(String.class),child.getKey()));
                }
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        latch.await();
        return areas;
    }
    public List<Place> getLocalitiesList(String area) throws InterruptedException {
        List<Place> locs=new LinkedList<>();
        DatabaseReference ref=locRef.child(area);
        CountDownLatch latch = new CountDownLatch(1);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    locs.add(new Place(child.getValue(String.class),child.getKey()));
                }
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        latch.await();
        return locs;
    }
}
