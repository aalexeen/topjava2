package ru.javaops.topjava2.web.voting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author alex_jd on 4/23/22
 * @project topjava2
 */
@RestController
@RequestMapping(value = AdminVotingController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminVotingController extends AbstractVotingController {

    static final String REST_URL = "/api/admin";

    @Override
    @DeleteMapping("/voting/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
