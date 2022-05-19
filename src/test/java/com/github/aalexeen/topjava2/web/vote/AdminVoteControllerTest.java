package com.github.aalexeen.topjava2.web.vote;

import com.github.aalexeen.topjava2.repository.VoteRepository;
import com.github.aalexeen.topjava2.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.aalexeen.topjava2.web.user.UserTestData.ADMIN_MAIL;
import static com.github.aalexeen.topjava2.web.vote.VoteTestData.VOTES;
import static com.github.aalexeen.topjava2.web.vote.VoteTestData.VOTE_TO_MATCHER;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author alex_jd on 4/28/22
 * @project topjava2
 */
public class AdminVoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL = AdminVoteController.REST_URL + '/';

    @Autowired
    private VoteRepository voteRepository;


    /*@Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(VOTES));
    }*/
}
