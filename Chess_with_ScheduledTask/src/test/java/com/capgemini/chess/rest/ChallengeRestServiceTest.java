package com.capgemini.chess.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.capgemini.chess.ChessApplication;
import com.capgemini.chess.enums.ChallengeStatus;
import com.capgemini.chess.service.ChallengeService;
import com.capgemini.chess.service.to.ChallengeTO;

/**
 * Test class to challenge rest service.
 * 
 * @author OORZECHO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ChessApplication.class)
@WebAppConfiguration
public class ChallengeRestServiceTest {

	@Mock
	private ChallengeService challengeService;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	/**
	 * Setup function called before every test.
	 */
	@Before
	public final void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.reset(challengeService);
		ReflectionTestUtils.setField(wac.getBean(ChallengeRestService.class), "challengeService", challengeService);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	/**
	 * Checks an operation of showing all challenges. Correct response is status
	 * ok and challenge list with all challenges.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testShouldGetAllChallenges() throws Exception {
		// given
		final ChallengeTO challenge = new ChallengeTO();
		challenge.setChallengeDate(new Date());
		challenge.setChallengeId(1L);
		challenge.setFirstPlayerId(2L);
		challenge.setSecondPlayerId(3L);
		challenge.setStatus(ChallengeStatus.SENT);
		List<ChallengeTO> challenges = new ArrayList<>();
		challenges.add(challenge);
		Mockito.when(challengeService.findAll()).thenReturn(challenges);

		// when
		ResultActions response = this.mockMvc.perform(get("/rest/challenges").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content("1"));

		// then
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("[0].challengeId").value(challenge.getChallengeId().intValue()))
				.andExpect(jsonPath("[0].status").value(challenge.getStatus().toString()))
				.andExpect(jsonPath("[0].firstPlayerId").value(challenge.getFirstPlayerId().intValue()))
				.andExpect(jsonPath("[0].secondPlayerId").value(challenge.getSecondPlayerId().intValue()));
	}

	/**
	 * Checks an operation of showing a challenge with given id. Correct
	 * response is status ok and challenge with requested id.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testShouldGetChallengeById() throws Exception {
		// given
		final ChallengeTO challenge = new ChallengeTO();
		challenge.setChallengeDate(new Date());
		challenge.setChallengeId(1L);
		challenge.setFirstPlayerId(2L);
		challenge.setSecondPlayerId(3L);
		challenge.setStatus(ChallengeStatus.SENT);
		Mockito.when(challengeService.findChallengeById(Mockito.anyLong())).thenReturn(challenge);

		// when
		ResultActions response = this.mockMvc.perform(get("/rest/challengeById").param("challengeId", "1")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content("1"));

		// then
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("challengeId").value(challenge.getChallengeId().intValue()))
				.andExpect(jsonPath("status").value(challenge.getStatus().toString()))
				.andExpect(jsonPath("firstPlayerId").value(challenge.getFirstPlayerId().intValue()))
				.andExpect(jsonPath("secondPlayerId").value(challenge.getSecondPlayerId().intValue()));
	}

	/**
	 * Checks an operation of showing challenges of a given user. Correct
	 * response is status ok and challenge list with all challenges of the user.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testShouldGetChallengesByUser() throws Exception {
		// given
		final ChallengeTO challenge = new ChallengeTO();
		challenge.setChallengeDate(new Date());
		challenge.setChallengeId(1L);
		challenge.setFirstPlayerId(2L);
		challenge.setSecondPlayerId(3L);
		challenge.setStatus(ChallengeStatus.SENT);
		List<ChallengeTO> challenges = new ArrayList<>();
		challenges.add(challenge);
		Mockito.when(challengeService.findChallengesByUserId(Mockito.anyLong())).thenReturn(challenges);

		// when
		ResultActions response = this.mockMvc.perform(get("/rest/challengeByUser").param("userId", "3")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content("1"));

		// then
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("[0].challengeId").value(challenge.getChallengeId().intValue()))
				.andExpect(jsonPath("[0].status").value(challenge.getStatus().toString()))
				.andExpect(jsonPath("[0].firstPlayerId").value(challenge.getFirstPlayerId().intValue()))
				.andExpect(jsonPath("[0].secondPlayerId").value(challenge.getSecondPlayerId().intValue()));
	}

	/**
	 * Checks an operation of creating a new challenge for users with given ids.
	 * Correct response is status ok and challenge with requested user ids.
	 * Function make challenge from challenge service should be called ones.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testShouldMakeChallenge() throws Exception {
		// given
		final ChallengeTO challenge = new ChallengeTO();
		challenge.setChallengeDate(new Date());
		challenge.setChallengeId(1L);
		challenge.setFirstPlayerId(2L);
		challenge.setSecondPlayerId(3L);
		challenge.setStatus(ChallengeStatus.SENT);
		Mockito.when(challengeService.makeChallenge(Mockito.anyLong(), Mockito.anyLong())).thenReturn(challenge);

		// when
		ResultActions response = this.mockMvc
				.perform(post("/rest/makeChallenge").param("firstPlayerId", "2").param("secondPlayerId", "3")
						.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content("1"));

		// then
		Mockito.verify(challengeService).makeChallenge(2L, 3L);
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("challengeId").value(challenge.getChallengeId().intValue()))
				.andExpect(jsonPath("status").value(challenge.getStatus().toString()))
				.andExpect(jsonPath("firstPlayerId").value(challenge.getFirstPlayerId().intValue()))
				.andExpect(jsonPath("secondPlayerId").value(challenge.getSecondPlayerId().intValue()));
	}

	/**
	 * Checks an operation of deleting a challenge with a given id. Correct
	 * response is status ok. Function delete challenge from challenge service
	 * should be called ones with requested challenge id parameter.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testShouldDeleteChallenge() throws Exception {
		// given

		// when
		ResultActions response = this.mockMvc.perform(delete("/rest/deleteChallenge").param("challengeId", "1")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content("1"));

		// then
		Mockito.verify(challengeService).deleteChallenge(1L);
		response.andExpect(status().isOk());
	}

	/**
	 * Checks an operation of changing a challenge status with a given id to
	 * rejected. Correct response is status ok and a challenge. Function reject
	 * challenge from challenge service should be called ones with requested
	 * challenge id parameter.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testShouldRejectChallenge() throws Exception {
		// given
		final ChallengeTO challenge = new ChallengeTO();
		challenge.setChallengeDate(new Date());
		challenge.setChallengeId(1L);
		challenge.setFirstPlayerId(2L);
		challenge.setSecondPlayerId(3L);
		challenge.setStatus(ChallengeStatus.SENT);
		Mockito.when(challengeService.rejectChallenge(Mockito.anyLong())).thenReturn(challenge);

		// when
		ResultActions response = this.mockMvc.perform(put("/rest/rejectChallenge").param("challengeId", "1")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content("1"));

		// then
		Mockito.verify(challengeService).rejectChallenge(1L);
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("challengeId").value(challenge.getChallengeId().intValue()))
				.andExpect(jsonPath("firstPlayerId").value(challenge.getFirstPlayerId().intValue()))
				.andExpect(jsonPath("secondPlayerId").value(challenge.getSecondPlayerId().intValue()));
	}

	/**
	 * Checks an operation of changing a challenge status with a given id to
	 * accepted. Correct response is status ok and a challenge. Function accept
	 * challenge from challenge service should be called ones with requested
	 * challenge id parameter.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testShouldAcceptChallenge() throws Exception {
		// given
		final ChallengeTO challenge = new ChallengeTO();
		challenge.setChallengeDate(new Date());
		challenge.setChallengeId(1L);
		challenge.setFirstPlayerId(2L);
		challenge.setSecondPlayerId(3L);
		challenge.setStatus(ChallengeStatus.SENT);
		Mockito.when(challengeService.acceptChallenge(Mockito.anyLong())).thenReturn(challenge);

		// when
		ResultActions response = this.mockMvc.perform(put("/rest/acceptChallenge").param("challengeId", "1")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content("1"));

		// then
		Mockito.verify(challengeService).acceptChallenge(1L);
		response.andExpect(status().isOk())//
				.andExpect(jsonPath("challengeId").value(challenge.getChallengeId().intValue()))
				.andExpect(jsonPath("firstPlayerId").value(challenge.getFirstPlayerId().intValue()))
				.andExpect(jsonPath("secondPlayerId").value(challenge.getSecondPlayerId().intValue()));
	}

}