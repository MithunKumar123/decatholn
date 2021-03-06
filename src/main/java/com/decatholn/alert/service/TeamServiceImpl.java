package com.decatholn.alert.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.decatholn.alert.exception.TeamStructureException;
import com.decatholn.alert.model.Developer;
import com.decatholn.alert.model.Team;
import com.decatholn.alert.repository.DeveloperRepo;
import com.decatholn.alert.repository.TeamRepo;


@Service
public class TeamServiceImpl implements ITeamService {

	@Autowired
	TeamRepo teamRepo;
	
	
	@Autowired
	DeveloperRepo developerRepo;

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

		Optional<List<Developer>> teams = developerRepo.findByTeam(team_id);
		
		List<Developer> team = teams.isPresent()?teams.get():null;

		StringBuilder developerNames = new StringBuilder();
		
		developerNames.append("The developers ");

		for (Developer developer : team) {
			
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
