package com.github.aalexeen.topjava2.web.vote;

import com.github.aalexeen.topjava2.model.Vote;
import com.github.aalexeen.topjava2.repository.VoteRepository;
import com.github.aalexeen.topjava2.to.VoteTo;
import com.github.aalexeen.topjava2.util.JsonUtil;
import com.github.aalexeen.topjava2.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.aalexeen.topjava2.web.user.UserTestData.USER_MAIL;
import static com.github.aalexeen.topjava2.web.vote.VoteTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author alex_jd on 4/28/22
 * @project topjava2
 */
public class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteController.REST_URL + '/';

    @Autowired
    private VoteRepository voteRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTE_8));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "byDay")
                .param("localDate", "2005-05-06"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VOTE1_ID))
                .andExpect(status().isUnauthorized());
    }


    @Test
    @WithUserDetails(value = USER_MAIL)
    void createWithLocation() throws Exception {
        voteRepository.deleteExisted(8);
        VoteTo newVoteTo = getVotingTo();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVoteTo)))
                .andExpect(status().isCreated());

        Vote newVote = getNewFromTo(newVoteTo);
        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
    }
}
