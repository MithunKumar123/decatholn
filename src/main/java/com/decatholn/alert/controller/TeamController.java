package com.decatholn.alert.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.decatholn.alert.model.Team;
import com.decatholn.alert.service.ITeamService;

@RestController
public class TeamController {
	
	@Autowired
	ITeamService iTeamService;
	
	@PostMapping(value = "/create")
	public ResponseEntity<Team> createTeam(@RequestBody Team team){
		Team createdTeam = iTeamService.createTeam(team);
		return new ResponseEntity<Team>(createdTeam, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/alertTeam/{team_id}")
	public ResponseEntity<String> alertTeam(@PathVariable Integer team_id){
		
		String devNames = iTeamService.alertTeam(team_id);
		
		return new ResponseEntity<String>(devNames, HttpStatus.OK);
		
	}

}
