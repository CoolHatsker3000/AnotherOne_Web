package com.ngtu.work.dyploma.controllers;

import com.ngtu.work.dyploma.firebase.TicketListContainer;
import com.ngtu.work.dyploma.views.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("tickets")
public class TicketListController {
    @Autowired
    private TicketListContainer ticketListContainer;

    @RequestMapping
    public String getTickets(Model model){
        List<Ticket> tickets=ticketListContainer.getTicketsList();
        model.addAttribute("tickets",tickets);
        return "ticketsListPage";

    }

    @RequestMapping(value="/{ticketId}",method = RequestMethod.GET)
    public String getTicket(Model model, @PathVariable String ticketId){

        Ticket ticket=ticketListContainer.getTicketById(ticketId);
        model.addAttribute("ticket",ticket);
        List<Ticket> tickets=ticketListContainer.getTicketsList();
        model.addAttribute("tickets",tickets);
        return "ticketPage";
    }

    @RequestMapping(value="/{ticketId}/to{newStatus}")
    public String changeTicketStatus(Model model, @PathVariable String ticketId, @PathVariable int newStatus){
        ticketListContainer.changeTicketStatus(ticketId,newStatus);
        return "redirect:/tickets/"+ticketId;
    }
}
