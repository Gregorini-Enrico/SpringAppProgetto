package gg.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import gg.project.service.PrincipalService;

@RestController	
public class Controller {
	
	@Autowired
	PrincipalService service;

}
