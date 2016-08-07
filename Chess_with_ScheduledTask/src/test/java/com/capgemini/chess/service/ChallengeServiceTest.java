package com.capgemini.chess.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.chess.ChessApplication;
import com.capgemini.chess.dataaccess.dao.PlayerDao;
import com.capgemini.chess.enums.ChallengeStatus;
import com.capgemini.chess.service.mapper.PlayerMapper;
import com.capgemini.chess.service.to.ChallengeTO;
import com.capgemini.chess.service.to.PlayerTO;

/**
 * Tests of challenge service functions.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ChessApplication.class)
public class ChallengeServiceTest {

	@Autowired
	private ChallengeService challengeService;

	@Autowired
	private PlayerDao playerDao;

	/**
	 * Checks an operation of creating a challenge.
	 */
	@Test
	public final void shouldMakeChallenge() {
		// given
		PlayerTO firstPlayer = new PlayerTO();
		firstPlayer.setId(1L);
		PlayerTO secondPlayer = new PlayerTO();
		secondPlayer.setId(2L);
		playerDao.save(PlayerMapper.map(firstPlayer));
		playerDao.save(PlayerMapper.map(secondPlayer));
		// when
		ChallengeTO challengeTO = challengeService.makeChallenge(1L, 2L);
		// then
		assertNotNull(challengeTO);
		assertEquals(ChallengeStatus.SENT, challengeTO.getStatus());
		assertEquals(1, challengeTO.getFirstPlayerId().longValue());
		assertEquals(2, challengeTO.getSecondPlayerId().longValue());
		assertNotNull(challengeTO.getChallengeId());
	}

	/**
	 * Checks an operation of changing challenge status to accepted.
	 */
	@Test
	public final void shouldAcceptChallenge() {
		// given
		PlayerTO firstPlayer = new PlayerTO();
		firstPlayer.setId(1L);
		PlayerTO secondPlayer = new PlayerTO();
		secondPlayer.setId(2L);
		playerDao.save(PlayerMapper.map(firstPlayer));
		playerDao.save(PlayerMapper.map(secondPlayer));
		ChallengeTO challengeTO = challengeService.makeChallenge(1L, 2L);
		// when
		challengeTO = challengeService.acceptChallenge(challengeTO.getChallengeId());
		// then
		assertNotNull(challengeTO);
		assertEquals(ChallengeStatus.ACCEPTED, challengeTO.getStatus());
		assertEquals(1, challengeTO.getFirstPlayerId().longValue());
		assertEquals(2, challengeTO.getSecondPlayerId().longValue());
		assertNotNull(challengeTO.getChallengeId());
	}

	/**
	 * Checks an operation of changing challenge status to rejected.
	 */
	@Test
	public final void shouldRejectChallenge() {
		// given
		PlayerTO firstPlayer = new PlayerTO();
		firstPlayer.setId(1L);
		PlayerTO secondPlayer = new PlayerTO();
		secondPlayer.setId(2L);
		playerDao.save(PlayerMapper.map(firstPlayer));
		playerDao.save(PlayerMapper.map(secondPlayer));
		ChallengeTO challengeTO = challengeService.makeChallenge(1L, 2L);
		// when
		challengeTO = challengeService.rejectChallenge(challengeTO.getChallengeId());
		// then
		assertNotNull(challengeTO);
		assertEquals(ChallengeStatus.REJECTED, challengeTO.getStatus());
		assertEquals(1, challengeTO.getFirstPlayerId().longValue());
		assertEquals(2, challengeTO.getSecondPlayerId().longValue());
		assertNotNull(challengeTO.getChallengeId());
	}

	/**
	 * Checks an operation of finding all challenges.
	 */
	@Test
	public final void shouldFindAllChallenges() {
		// given
		PlayerTO firstPlayer = new PlayerTO();
		firstPlayer.setId(1L);
		PlayerTO secondPlayer = new PlayerTO();
		secondPlayer.setId(2L);
		playerDao.save(PlayerMapper.map(firstPlayer));
		playerDao.save(PlayerMapper.map(secondPlayer));
		challengeService.makeChallenge(1L, 2L);
		// when
		List<ChallengeTO> challenges = challengeService.findAll();
		// then
		assertNotNull(challenges);
		assertNotNull(challenges.get(0));
		assertEquals(1, challenges.get(0).getFirstPlayerId().longValue());
		assertEquals(2, challenges.get(0).getSecondPlayerId().longValue());
		assertNotNull(challenges.get(0).getChallengeId());
	}

}
