package com.decatholn.alert.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.decatholn.alert.model.Developer;

@Repository
public interface DeveloperRepo extends JpaRepository<Developer, UUID> {

}
