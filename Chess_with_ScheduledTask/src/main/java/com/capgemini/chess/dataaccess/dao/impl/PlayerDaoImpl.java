package com.capgemini.chess.dataaccess.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.capgemini.chess.dataaccess.dao.PlayerDao;
import com.capgemini.chess.dataaccess.entities.PlayerEntity;
import com.capgemini.chess.enums.Level;

@Repository
public class PlayerDaoImpl implements PlayerDao {

	private Set<PlayerEntity> players = new HashSet<>();
	private static long idGenerator = 1;

	@Override
	public final List<PlayerEntity> findAll() {
		return new ArrayList<>(players);
	}

	@Override
	public final PlayerEntity findPlayerById(final Long playerId) {
		PlayerEntity foundPlayer = null;
		for (PlayerEntity player : players) {
			if (player.getId() == playerId) {
				foundPlayer = player;
			}
		}
		return foundPlayer;
	}

	@Override
	public final long generatePlayerId() {
		return idGenerator++;
	}

	@Override
	public final PlayerEntity save(final PlayerEntity player) {
		players.add(player);
		return player;
	}

	@Override
	public final void generateTestPlayers() {
		players.add(new PlayerEntity(1L, Level.NEWBIE, 130, 20, 10, 5, 5));
		players.add(new PlayerEntity(2L, Level.NEWBIE, 35, 12, 2, 5, 5));
		players.add(new PlayerEntity(3L, Level.NEWBIE, 43, 5, 1, 3, 1));
		players.add(new PlayerEntity(4L, Level.NEWBIE, 13, 12, 6, 2, 4));
	}
}
