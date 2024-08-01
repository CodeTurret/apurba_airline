package com.apurba.airline.Controller;

import com.apurba.airline.Service.SalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/sales")
@Tag(name = "Sales", description = "Endpoints for managing sales data")
public class SalesController {

    private static final Logger logger = LoggerFactory.getLogger(SalesController.class);

    @Autowired
    private SalesService salesService;

    @GetMapping("/total-amount")
    @Operation(
            summary = "Get Total Sales Amount",
            description = "Retrieves the total sales amount for a given date.",
            responses = {
                    @ApiResponse(description = "Successful retrieval of the total sales amount", responseCode = "200"),
                    @ApiResponse(description = "Invalid date format", responseCode = "400")
            }
    )
    public double getTotalSalesAmount(
            @Parameter(description = "Date for which to retrieve the total sales amount", example = "2024-08-01")
            @RequestParam("date") LocalDate date) {
        logger.info("Received request to get total sales amount for date: {}", date);
        double amount = salesService.getTotalSalesAmount(date);
        logger.info("Total sales amount for date {}: {}", date, amount);
        return amount;
    }

    @GetMapping("/max-sale-day")
    @Operation(
            summary = "Get Day with Maximum Sales",
            description = "Retrieves the day with the maximum sales amount between two dates.",
            responses = {
                    @ApiResponse(description = "Successful retrieval of the day with maximum sales", responseCode = "200"),
                    @ApiResponse(description = "Invalid date range format", responseCode = "400")
            }
    )
    public LocalDate getMaxSaleDay(
            @Parameter(description = "Start date for the sales period", example = "2024-01-01")
            @RequestParam("startDate") LocalDate startDate,
            @Parameter(description = "End date for the sales period", example = "2024-12-31")
            @RequestParam("endDate") LocalDate endDate) {
        logger.info("Received request to get max sale day between dates: {} and {}", startDate, endDate);
        LocalDate maxSaleDay = salesService.getMaxSaleDay(startDate, endDate);
        logger.info("Max sale day between dates {} and {}: {}", startDate, endDate, maxSaleDay);
        return maxSaleDay;
    }

    @GetMapping("/top-selling-routes")
    @Operation(
            summary = "Get Top Selling Routes",
            description = "Retrieves a list of the top-selling routes.",
            responses = {
                    @ApiResponse(description = "Successful retrieval of top-selling routes", responseCode = "200")
            }
    )
    public List<String> getTopSellingRoutes() {
        logger.info("Received request to get top selling routes");
        List<String> topSellingRoutes = salesService.getTopSellingRoutes();
        logger.info("Top selling routes: {}", topSellingRoutes);
        return topSellingRoutes;
    }
}