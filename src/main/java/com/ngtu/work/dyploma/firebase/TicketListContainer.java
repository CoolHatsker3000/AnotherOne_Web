package com.ngtu.work.dyploma.firebase;

import com.google.firebase.database.*;
import com.ngtu.work.dyploma.views.Ticket;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class TicketListContainer {
    private DatabaseReference ref;
    private DataSnapshot ds;
    private ValueEventListener listener;

    public TicketListContainer() {
        ref= FirebaseDatabase.getInstance().getReference("tickets");
        listener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ds=dataSnapshot;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        ref.addValueEventListener(listener);

    }

    public List<Ticket> getTicketsList(){
        LinkedList<Ticket> tickets=new LinkedList<>();
        for (DataSnapshot users:ds.getChildren()){
            for (DataSnapshot ticket: users.getChildren()){
                tickets.add(ticket.getValue(Ticket.class));
            }
        }
        return tickets;
    }

    public Ticket getTicketById(String id){
        Ticket result;
        for (DataSnapshot users:ds.getChildren()){
            for (DataSnapshot ticket: users.getChildren()){
                result=ticket.getValue(Ticket.class);
                if (result.id.equals(id)){
                    result.uid=users.getKey();
                    return result;
                }
            }
        }
        return null;
    }

    public void changeTicketStatus(String ticketId, int status){
        Ticket ticket=getTicketById(ticketId);
        Map<String, Object> ticketUpdate = new HashMap<>();
        ticketUpdate.put(ticket.id+"/status",status);
        ref.child(ticket.uid).updateChildrenAsync(ticketUpdate);//Value(status, new DatabaseReference.CompletionListener() {

    }

    //TODO:сделать нормальный список тикетов и создать здесь метод с квери
    public void getTicketByIdNew(String id){
        return;
    }

}
