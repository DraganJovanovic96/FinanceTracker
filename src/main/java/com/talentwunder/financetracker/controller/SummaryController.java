package com.talentwunder.financetracker.controller;

import com.talentwunder.financetracker.dto.SummaryDto;
import com.talentwunder.financetracker.service.SummaryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The SummaryController class is a REST controller which is responsible for handling HTTP requests related to Summary.
 * It communicates with the summary service.
 * The RequiredArgsConstructor is used for fetching transactionService from IoC container.
 *
 * @author Dragan Jovanovic
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("api/v1/summary")
@RequiredArgsConstructor
@CrossOrigin
public class SummaryController {
    /**
     * The service used to for summary.
     */
    private final SummaryService summaryService;

    /**
     * The endpoint accepts a GET request.
     * Retrieves the summary data for user.
     *
     * @return ResponseEntity {@link SummaryDto}  containing the summary's data for the specified user
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get summary")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Summary successfully fetched.", response = SummaryDto.class)
    })
    public ResponseEntity<SummaryDto> getSummary() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(summaryService.getSummary());
    }
}
