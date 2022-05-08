package com.github.aalexeen.topjava2.web.voting;

import com.github.aalexeen.topjava2.model.Voting;
import com.github.aalexeen.topjava2.repository.VotingRepository;
import com.github.aalexeen.topjava2.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.github.aalexeen.topjava2.to.VotingTo;
import com.github.aalexeen.topjava2.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.github.aalexeen.topjava2.web.user.UserTestData.USER_MAIL;
import static com.github.aalexeen.topjava2.web.voting.VotingTestData.*;

/**
 * @author alex_jd on 4/28/22
 * @project topjava2
 */
public class VotingControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VotingController.REST_URL + '/';

    @Autowired
    private VotingRepository votingRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + (VOTING1_ID + 7)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTING_MATCHER.contentJson(voting8));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + NONEXISTENT_VOTING_ID))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VOTING1_ID))
                .andExpect(status().isUnauthorized());
    }

    /*@Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTING_TO_MATCHER.contentJson(votings));
    }*/

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createWithLocation() throws Exception {
        votingRepository.deleteExisted(8);
        VotingTo newVotingTo = getVotingTo();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVotingTo)))
                .andExpect(status().isCreated());

        Voting newVoting = getNewFromTo(newVotingTo);
        Voting created = VOTING_MATCHER.readFromJson(action);
        int newId = created.id();
        newVoting.setId(newId);
        VOTING_MATCHER.assertMatch(created, newVoting);
        VOTING_MATCHER.assertMatch(votingRepository.getById(newId), newVoting);
    }
}
