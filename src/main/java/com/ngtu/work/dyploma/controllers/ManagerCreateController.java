package com.ngtu.work.dyploma.controllers;

import com.ngtu.work.dyploma.firebase.AreasListContainer;
import com.ngtu.work.dyploma.firebase.ManagerFirebase;
import com.ngtu.work.dyploma.views.Manager;
import com.ngtu.work.dyploma.views.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = "/create_mngr")
public class ManagerCreateController {

    @Autowired
    AreasListContainer areasListContainer;
    @Autowired
    ManagerFirebase managerFirebase;

    @RequestMapping(method = RequestMethod.GET)
    public String getAreas(Model model){
        List<Place> areas= new LinkedList<Place>();
        try {
            areas = areasListContainer.getAreasList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        model.addAttribute("areas",areas);
        model.addAttribute("locs",new LinkedList<>());
        return "managerCreationPage";
    }
    @RequestMapping(value = "/{areaId}",method = RequestMethod.GET)
    public String getAreasAndLocals(Model model, @PathVariable String areaId){
        List<Place> areas=new LinkedList<Place>();
        List<Place> locs= new LinkedList<Place>();
        try {
            areas=areasListContainer.getAreasList();
            locs = areasListContainer.getLocalitiesList(areaId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        model.addAttribute("areas",areas);
        model.addAttribute("chosenArea",areaId);
        model.addAttribute("locs",locs);
        return "managerCreationPage";
    }
    @RequestMapping(value = "/{areaId}/{locId}",method = RequestMethod.GET)
    public String getManagerCreationForm(Model model, @PathVariable String areaId, @PathVariable String locId){
        List<Place> areas=new LinkedList<Place>();
        List<Place> locs= new LinkedList<Place>();
        try {
            areas=areasListContainer.getAreasList();
            locs = areasListContainer.getLocalitiesList(areaId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        model.addAttribute("areas",areas);
        model.addAttribute("chosenArea",areaId);
        model.addAttribute("locs",locs);
        model.addAttribute("chosenLoc",locId);
        model.addAttribute("manager",new Manager());
        return "managerCreationPage";
    }
    @RequestMapping(value = "/{areaId}/{locId}/create",method = RequestMethod.POST)
    public String createManager(Model model, @ModelAttribute Manager manager, @PathVariable String areaId, @PathVariable String locId){
        manager.area=areaId;
        manager.locality=locId;
        try {
            managerFirebase.addManager(manager);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "redirect:/tickets";
    }
}
