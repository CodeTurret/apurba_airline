package com.apurba.airline.Service;

import com.apurba.airline.Model.Food;
import com.apurba.airline.Model.Ticket;
import com.apurba.airline.Repository.FoodRepository;
import com.apurba.airline.Repository.RouteRepository;
import com.apurba.airline.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SalesService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private RouteRepository routeRepository;

    public double getTotalSalesAmount(LocalDate date) {
        List<Ticket> tickets = ticketRepository.findBySaleDate(date);
        List<Food> foods = foodRepository.findBySaleDate(date);

        double ticketSales = tickets.stream().mapToDouble(Ticket::getPrice).sum();
        double foodSales = foods.stream().mapToDouble(Food::getPrice).sum();

        return ticketSales + foodSales;
    }


    public LocalDate getMaxSaleDay(LocalDate startDate, LocalDate endDate) {
        List<Ticket> tickets = ticketRepository.findBySaleDateBetween(startDate, endDate);

        return tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getSaleDate, Collectors.summingDouble(Ticket::getPrice)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public List<String> getTopSellingRoutes() {
        List<Ticket> tickets = ticketRepository.findAll();

        return tickets.stream()
                .collect(Collectors.groupingBy(ticket -> ticket.getFlight().getRoute().getId(), Collectors.summingDouble(Ticket::getPrice)))
                .entrySet().stream()
                .sorted(Map.Entry.<Long, Double>comparingByValue().reversed())
                .limit(3)
                .map(entry -> routeRepository.findById(entry.getKey()).map(route -> route.getOrigin() + " - " + route.getDestination()).orElse(""))
                .collect(Collectors.toList());
    }
}