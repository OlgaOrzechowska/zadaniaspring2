package com.capgemini.chess.service;

import org.springframework.stereotype.Service;

/**
 * Service to delete outdated challenges.
 */
@Service
public interface CleanUpService {

	/**
	 * Deletes outdated challenges.
	 */
	void deleteOutdatedChallenges();

}
