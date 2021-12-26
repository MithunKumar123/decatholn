package com.decatholn.alert.service;

import java.util.List;
import java.util.UUID;

import com.decatholn.alert.model.Team;

public interface ITeamService {
	
	Team createTeam(Team team);
	
	String alertTeam(Integer team_id);

	Team getTeam(Integer id);

}
