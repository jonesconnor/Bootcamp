package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Player {
	@Id
	private String playerid;
	private String playername;
	private int score;
}
