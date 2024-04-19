package com.techtalk.techtalkapi.controller;

import com.techtalk.techtalkapi.application.search.SearchResult;
import com.techtalk.techtalkapi.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/search")
@AllArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/{key}")
    public SearchResult search(@PathVariable String key) {
        return searchService.search(key);
    }
}
