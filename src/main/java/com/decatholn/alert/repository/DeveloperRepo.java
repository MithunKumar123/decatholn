package com.decatholn.alert.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.decatholn.alert.model.Developer;

@Repository
public interface DeveloperRepo extends JpaRepository<Developer, Integer> {

	@Query(value="SELECT d from Developer d where d.team.id=:team_id")
	Optional<List<Developer>> findByTeam(Integer team_id);

}
