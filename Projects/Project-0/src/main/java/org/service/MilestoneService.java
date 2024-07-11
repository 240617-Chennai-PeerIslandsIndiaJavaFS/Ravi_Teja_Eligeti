package org.service;

import org.dao.MileStoneDaoImplementation;
import org.models.Milestone;

public class MilestoneService {
    MileStoneDaoImplementation mdao;

    public  MilestoneService(){
        mdao=new MileStoneDaoImplementation();
    }

    public Milestone getMilestoneById(int id){
       return mdao.getMilestoneById(id);
    }
}
