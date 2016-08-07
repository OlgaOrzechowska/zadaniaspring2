package com.capgemini.chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.capgemini.chess.aop.ChallengeDaoAspect;
import com.capgemini.chess.aop.PlayerDaoAspect;

/**
 * Chess application launcher.
 */
@EnableScheduling
@SpringBootApplication
public class ChessApplication {

	/**
	 * Main function of the application.
	 * 
	 * @param args
	 *            arguments
	 */
	public static void main(final String[] args) {
		SpringApplication.run(ChessApplication.class, args);
	}

	@Bean
	public ChallengeDaoAspect challengeDaoAspect() {
		return new ChallengeDaoAspect();
	}

	@Bean
	public PlayerDaoAspect playerDaoAspect() {
		return new PlayerDaoAspect();
	}
}
