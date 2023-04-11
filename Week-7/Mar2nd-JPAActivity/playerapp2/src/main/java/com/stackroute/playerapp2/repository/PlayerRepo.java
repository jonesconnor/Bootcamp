package com.stackroute.playerapp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.playerapp2.model.Player;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Integer>{

}
