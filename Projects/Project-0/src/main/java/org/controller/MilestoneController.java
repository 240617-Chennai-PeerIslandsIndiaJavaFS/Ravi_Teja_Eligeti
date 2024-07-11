package org.controller;

import org.models.Milestone;
import org.service.MilestoneService;

public class MilestoneController {
    public Milestone getMilestoneById(int id){
        return new MilestoneService().getMilestoneById(id);
    }
}
