package com.capgemini.chess.service;

import java.util.List;

import com.capgemini.chess.service.to.ChallengeTO;

public interface ChallengeService {

	/**
	 * Changes challenge status to accepted.
	 * 
	 * @param challengeId
	 *            id of a challenge to accept
	 * @return challenge
	 */
	ChallengeTO acceptChallenge(Long challengeId);

	/**
	 * Changes challenge status to rejected.
	 * 
	 * @param challengeId
	 *            id of a challenge to reject
	 * @return challenge
	 */
	ChallengeTO rejectChallenge(Long challengeId);

	/**
	 * Creates a new challenge.
	 * 
	 * @param firstPlayerId
	 *            id of the first player
	 * @param secondPlayerId
	 *            id of the second player
	 * @return challenge
	 */
	ChallengeTO makeChallenge(Long firstPlayerId, Long secondPlayerId);

	/**
	 * Shows all challenges.
	 * 
	 * @return list of all challenges
	 */
	List<ChallengeTO> findAll();

	/**
	 * Finds challenge with given id.
	 * 
	 * @param id
	 *            challenge id
	 * @return challenge with given id
	 */
	ChallengeTO findChallengeById(Long id);

	/**
	 * Finds challenges with given user.
	 * 
	 * @param userId
	 *            user id
	 * @return challenge with given user
	 */
	List<ChallengeTO> findChallengesByUserId(Long userId);

	/**
	 * Deletes challenge with given id.
	 * 
	 * @param id
	 *            challenge id
	 */
	void deleteChallenge(Long challengeId);

	/**
	 * Generates some challenges to be used in tests.
	 */
	void generateTestChallenges();
}
