package com.decatholn.alert.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.decatholn.alert.model.Developer;
import com.decatholn.alert.model.Team;
import com.decatholn.alert.repository.DeveloperRepo;
import com.decatholn.alert.service.ITeamService;

@RestController
public class TeamController {
	
	@Autowired
	ITeamService iTeamService;
	
	@Autowired
	DeveloperRepo developerRepo;
	
	@PostMapping(value = "/create")
	public ResponseEntity<Team> createTeam(@RequestBody Team team){
		Team createdTeam = iTeamService.createTeam(team);
		return new ResponseEntity<Team>(createdTeam, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/alertTeam/{team_id}")
	public ResponseEntity<String> alertTeam(@PathVariable("team_id") Integer team_id){
		
		String devNames = iTeamService.alertTeam(team_id);
		
		return new ResponseEntity<String>(devNames, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/getDeveloper/{dev_id}")
	public Developer getDeveloper(@PathVariable("dev_id") Integer dev_id) {
		Optional<Developer> response = developerRepo.findById(dev_id);
		return response.get();
	}

}
