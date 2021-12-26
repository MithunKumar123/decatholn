package com.decatholn.alert.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.decatholn.alert.model.Team;

@Repository
public interface TeamRepo extends JpaRepository<Team, Integer> {

}
