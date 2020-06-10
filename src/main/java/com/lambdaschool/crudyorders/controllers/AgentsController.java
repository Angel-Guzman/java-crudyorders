package com.lambdaschool.crudyorders.controllers;

import com.lambdaschool.crudyorders.models.Agents;
import com.lambdaschool.crudyorders.models.Customers;
import com.lambdaschool.crudyorders.services.AgentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agents")
public class AgentsController
{
    @Autowired
    private AgentsService agentsService;

    // http://localhost:2020/agents/agent/9
    @GetMapping(value = "/agent/{id}", produces = {"application/json"})
    public ResponseEntity<?> listAgentsById(@PathVariable long id)
    {
        Agents a = agentsService.findAgentsById(id);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
}
