package com.decatholn.alert.service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.decatholn.alert.exception.TeamStructureException;
import com.decatholn.alert.model.Developer;
import com.decatholn.alert.model.Team;
import com.decatholn.alert.repository.TeamRepo;


@Service
public class TeamServiceImpl implements ITeamService {

	@Autowired
	TeamRepo teamRepo;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${external-api-call}")
	private String uri;

	@Override
	public Team createTeam(Team team) {
		
		try {
			if(team.getDevelopers().size() == 0) {
				throw new Exception();
			}
			return teamRepo.save(team);
		}catch(Exception e) {
			throw new TeamStructureException("At least one developer should be there in the Team");
		}
	}

	@Override
	public String alertTeam(Integer team_id) {

		Team team = teamRepo.getById(team_id);

		StringBuilder developerNames = new StringBuilder();
		
		developerNames.append(MessageFormat.format("The {0} team developers ", team.getTeamName()).toString());

		for (Developer developer : team.getDevelopers()) {
			
			Map<String, String> payloadRequest = new HashMap<>();

			payloadRequest.put("phone_number", developer.getPhoneNumber());

			String response = restTemplate.postForObject(uri, payloadRequest, String.class);

			if (response.contains("Success")) {
				developerNames.append(developer.getDevName());
			}
			
			developerNames.append(", ");

		}
		
		developerNames.append("have been alerted");

		return developerNames.toString();
	}

	@Override
	public Team getTeam(Integer id) {
		
		return teamRepo.getById(id);
	}

}
