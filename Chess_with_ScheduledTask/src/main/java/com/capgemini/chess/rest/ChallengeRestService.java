package com.capgemini.chess.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.chess.service.ChallengeService;
import com.capgemini.chess.service.to.ChallengeTO;

/**
 * Rest controller class to challenges.
 * 
 * @author OORZECHO
 */
@Controller
@ResponseBody
public class ChallengeRestService {

	@Autowired
	private ChallengeService challengeService;

	/**
	 * Shows all challenges.
	 * 
	 * @return list of all challenges and http status
	 */
	@RequestMapping(value = "rest/challenges", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final ResponseEntity<List<ChallengeTO>> getChallenges() {
		List<ChallengeTO> challenges = challengeService.findAll();
		return new ResponseEntity<List<ChallengeTO>>(challenges, HttpStatus.OK);
	}

	/**
	 * Shows challenge with given id.
	 * 
	 * @param challengeId
	 *            id of requested challenge
	 * 
	 * @return the challenge and http status
	 */
	@RequestMapping(value = "rest/challengeById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final ResponseEntity<ChallengeTO> getChallengesById(@RequestParam("challengeId") final Long challengeId) {
		ChallengeTO challenge = challengeService.findChallengeById(challengeId);
		return new ResponseEntity<ChallengeTO>(challenge, HttpStatus.OK);
	}

	/**
	 * Shows challenges of a given user.
	 * 
	 * @param userId
	 *            id of a user
	 * 
	 * @return list of challenges of the user and http status
	 */
	@RequestMapping(value = "rest/challengeByUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final ResponseEntity<List<ChallengeTO>> getChallengesByUserId(@RequestParam("userId") final Long userId) {
		List<ChallengeTO> challenges = challengeService.findChallengesByUserId(userId);
		return new ResponseEntity<List<ChallengeTO>>(challenges, HttpStatus.OK);
	}

	/**
	 * Creates challenge for two given players.
	 * 
	 * @param firstPlayerId
	 *            id of a first player
	 * @param secondPlayerId
	 *            id of a second player
	 * 
	 * @return created challenge and http status
	 */
	@RequestMapping(value = "rest/makeChallenge", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public final ResponseEntity<ChallengeTO> makeChallenge(@RequestParam("firstPlayerId") final Long firstPlayerId,
			@RequestParam("secondPlayerId") final Long secondPlayerId) {
		ChallengeTO challenge = challengeService.makeChallenge(firstPlayerId, secondPlayerId);
		return new ResponseEntity<ChallengeTO>(challenge, HttpStatus.OK);
	}

	/**
	 * Deletes challenge with given id.
	 * 
	 * @param challengeId
	 *            id of a challenge to delete
	 * 
	 * @return http status
	 */
	@RequestMapping(value = "rest/deleteChallenge", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public final HttpStatus deleteChallenge(@RequestParam("challengeId") final Long challengeId) {
		challengeService.deleteChallenge(challengeId);
		return HttpStatus.OK;
	}

	/**
	 * Changes status of a challenge with given id to rejected.
	 * 
	 * @param challengeId
	 *            id of a challenge to reject
	 * 
	 * @return rejected challenge and http status
	 */
	@RequestMapping(value = "rest/rejectChallenge", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public final ResponseEntity<ChallengeTO> rejectChallenge(@RequestParam("challengeId") final Long challengeId) {
		ChallengeTO challenge = challengeService.rejectChallenge(challengeId);
		return new ResponseEntity<ChallengeTO>(challenge, HttpStatus.OK);
	}

	/**
	 * Changes status of a challenge with given id to accepted.
	 * 
	 * @param challengeId
	 *            id of a challenge to accept
	 * 
	 * @return accepted challenge and http status
	 */
	@RequestMapping(value = "rest/acceptChallenge", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public final ResponseEntity<ChallengeTO> acceptChallenge(@RequestParam("challengeId") final Long challengeId) {
		ChallengeTO challenge = challengeService.acceptChallenge(challengeId);
		return new ResponseEntity<ChallengeTO>(challenge, HttpStatus.OK);
	}

	/**
	 * Generates few players and challenges for tests.
	 * 
	 * @return list of generated challenges and http status
	 */
	@RequestMapping(value = "rest/generate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public final ResponseEntity<List<ChallengeTO>> generateChallenges() {
		challengeService.generateTestChallenges();
		List<ChallengeTO> challenges = challengeService.findAll();
		return new ResponseEntity<List<ChallengeTO>>(challenges, HttpStatus.OK);
	}
}
