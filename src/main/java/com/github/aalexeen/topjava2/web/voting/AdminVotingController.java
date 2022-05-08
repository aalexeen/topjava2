package com.github.aalexeen.topjava2.web.voting;

import com.github.aalexeen.topjava2.model.Voting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author alex_jd on 4/23/22
 * @project topjava2
 */
@RestController
@RequestMapping(value = AdminVotingController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "voting")
public class AdminVotingController extends AbstractVotingController {

    static final String REST_URL = "/api/admin/voting";

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Voting> get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping()
    @Cacheable
    public List<Voting> getAll() {
        log.info("getAll");
        return votingRepository.findAll(Sort.by(Sort.Direction.DESC, "localDate", "localTime"));
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
