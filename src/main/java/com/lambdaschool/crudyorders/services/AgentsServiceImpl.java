package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Agents;
import com.lambdaschool.crudyorders.models.Customers;
import com.lambdaschool.crudyorders.repositories.AgentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "agentsService")
public class AgentsServiceImpl implements AgentsService
{
    @Autowired
    private AgentsRepository agentsrepos;

    @Override
    public Agents findAgentsById(long id)
    {
        return agentsrepos.findById(id).orElseThrow(() -> new EntityNotFoundException("Agent " + id + " Not Found "));
    }
}
