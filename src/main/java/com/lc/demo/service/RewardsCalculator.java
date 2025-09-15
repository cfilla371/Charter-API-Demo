package com.lc.demo.service;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class RewardsCalculator {

    public int calculateRewardPoints(BigDecimal transactionTotal) {
        if (transactionTotal == null) {
            return 0;
        }
        int total = transactionTotal.intValue();
        int points = 0;

        if (total > 100) {
            points += (total - 100) * 2;
            points += 50;
        } else if (total > 50) {
            points += (total - 50);
        }
        return points;
    }
}