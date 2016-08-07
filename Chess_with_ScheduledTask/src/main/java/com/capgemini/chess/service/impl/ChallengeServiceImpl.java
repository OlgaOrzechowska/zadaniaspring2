package com.capgemini.chess.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.chess.dataaccess.dao.ChallengeDao;
import com.capgemini.chess.dataaccess.dao.PlayerDao;
import com.capgemini.chess.dataaccess.entities.ChallengeEntity;
import com.capgemini.chess.dataaccess.entities.PlayerEntity;
import com.capgemini.chess.enums.ChallengeStatus;
import com.capgemini.chess.service.ChallengeService;
import com.capgemini.chess.service.mapper.ChallengeMapper;
import com.capgemini.chess.service.to.ChallengeTO;

@Service
public class ChallengeServiceImpl implements ChallengeService {

	@Autowired
	private ChallengeDao challengeDao;

	@Autowired
	private PlayerDao playerDao;

	@Override
	public final ChallengeTO makeChallenge(final Long firstPlayerId, final Long secondPlayerId) {

		ChallengeEntity challenge = new ChallengeEntity();
		PlayerEntity firstPlayerEntity = playerDao.findPlayerById(firstPlayerId);
		PlayerEntity secondPlayerEntity = playerDao.findPlayerById(secondPlayerId);

		challenge.setFirstPlayer(firstPlayerEntity);
		challenge.setSecondPlayer(secondPlayerEntity);
		challenge.setStatus(ChallengeStatus.SENT);
		challenge.setChallengeDate(new Date());

		challengeDao.save(challenge);
		return ChallengeMapper.map(challenge);
	}

	@Override
	public final ChallengeTO acceptChallenge(final Long challengeId) {
		ChallengeEntity challengeEntity = challengeDao.updateStatus(challengeId, ChallengeStatus.ACCEPTED);
		return ChallengeMapper.map(challengeEntity);
	}

	@Override
	public final ChallengeTO rejectChallenge(final Long challengeId) {
		ChallengeEntity challengeEntity = challengeDao.updateStatus(challengeId, ChallengeStatus.REJECTED);
		return ChallengeMapper.map(challengeEntity);
	}

	@Override
	public List<ChallengeTO> findAll() {
		List<ChallengeEntity> challengeEntities = challengeDao.findAll();
		return ChallengeMapper.map2TOs(challengeEntities);
	}

	@Override
	public ChallengeTO findChallengeById(Long id) {
		ChallengeEntity challengeEntity = challengeDao.findChallengeById(id);
		return ChallengeMapper.map(challengeEntity);
	}

	@Override
	public List<ChallengeTO> findChallengesByUserId(Long userId) {
		List<ChallengeEntity> challengeEntities = challengeDao.findChallengeByUserId(userId);
		return ChallengeMapper.map2TOs(challengeEntities);
	}

	@Override
	public void deleteChallenge(Long challengeId) {
		challengeDao.delete(challengeId);
	}

	@Override
	public void generateTestChallenges() {
		playerDao.generateTestPlayers();
		makeChallenge(1L, 3L);
		makeChallenge(2L, 3L);
		makeChallenge(3L, 4L);
	}
}
