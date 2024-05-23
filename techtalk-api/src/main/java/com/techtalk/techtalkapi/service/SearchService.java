package com.techtalk.techtalkapi.service;

import java.net.URLDecoder;
import com.techtalk.techtalkapi.application.search.SearchResult;
import com.techtalk.techtalkapi.data.SubjectRepository;
import com.techtalk.techtalkapi.data.UsersRepository;
import com.techtalk.techtalkapi.domain.model.Subject;
import com.techtalk.techtalkapi.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchService {
    private final SubjectRepository subjectRepository;
    private final UsersRepository usersRepository;

    public SearchResult search(String key) {
        log.info("Search Started with key: {}", key);
        try {
            String decodedKey = URLDecoder.decode(key, StandardCharsets.UTF_8);

            List<Subject> subjectList = subjectRepository.searchActiveSubjectsByKey(decodedKey);
            List<User> userList = usersRepository.searchUsersByUsername(decodedKey);

            log.info("Search Finished with key: {}", decodedKey);
            return new SearchResult(true, subjectList, userList);
        } catch (Exception ex) {
            log.error("Search Failed with key: {}", key);
            return new SearchResult(false, null, null);
        }
    }
}
