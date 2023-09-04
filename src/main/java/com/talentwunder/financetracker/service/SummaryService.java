package com.talentwunder.financetracker.service;

import com.talentwunder.financetracker.dto.SummaryDto;

/**
 * The SummaryService interface contains methods that will be implemented is SummaryServiceImpl and methods correlate
 * to Summary entity.
 *
 * @author Dragan Jovanovic
 * @version 1.0
 * @since 1.0
 */
public interface SummaryService {
    /**
     * Retrieves a summary.
     *
     * @return The SummaryDto
     */
    SummaryDto getSummary();
}
