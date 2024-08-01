package com.apurba.airline.service;

import com.apurba.airline.Model.Ticket;
import com.apurba.airline.Model.Food;
import com.apurba.airline.Model.Route;
import com.apurba.airline.Model.Flight;
import com.apurba.airline.Repository.TicketRepository;
import com.apurba.airline.Repository.FoodRepository;
import com.apurba.airline.Repository.RouteRepository;
import com.apurba.airline.Repository.FlightRepository;
import com.apurba.airline.Service.SalesService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SalesServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private RouteRepository routeRepository;

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private SalesService salesService;

    @Test
    public void testGetTotalSalesAmount() {
        LocalDate today = LocalDate.now();

        Ticket ticket1 = new Ticket();
        ticket1.setPrice(100.0);
        ticket1.setSaleDate(today);

        Ticket ticket2 = new Ticket();
        ticket2.setPrice(150.0);
        ticket2.setSaleDate(today);

        Food food1 = new Food();
        food1.setPrice(50.0);
        food1.setSaleDate(today);

        Food food2 = new Food();
        food2.setPrice(30.0);
        food2.setSaleDate(today);

        when(ticketRepository.findBySaleDate(today)).thenReturn(Arrays.asList(ticket1, ticket2));
        when(foodRepository.findBySaleDate(today)).thenReturn(Arrays.asList(food1, food2));

        double totalSales = salesService.getTotalSalesAmount(today);

        assertEquals(330.0, totalSales);
    }

    @Test
    public void testGetTopSellingRoutes() {
        Route route1 = new Route();
        route1.setId(1L);
        route1.setOrigin("NYC");
        route1.setDestination("LAX");

        Route route2 = new Route();
        route2.setId(2L);
        route2.setOrigin("SFO");
        route2.setDestination("ORD");

        Flight flight1 = new Flight();
        flight1.setId(1L);
        flight1.setRoute(route1);

        Flight flight2 = new Flight();
        flight2.setId(2L);
        flight2.setRoute(route2);

        Ticket ticket1 = new Ticket();
        ticket1.setPrice(100.0);
        ticket1.setFlight(flight1);

        Ticket ticket2 = new Ticket();
        ticket2.setPrice(150.0);
        ticket2.setFlight(flight1);

        Ticket ticket3 = new Ticket();
        ticket3.setPrice(200.0);
        ticket3.setFlight(flight2);

        when(ticketRepository.findAll()).thenReturn(Arrays.asList(ticket1, ticket2, ticket3));
        when(routeRepository.findById(1L)).thenReturn(Optional.of(route1));
        when(routeRepository.findById(2L)).thenReturn(Optional.of(route2));

        List<String> topSellingRoutes = salesService.getTopSellingRoutes();

        assertEquals(2, topSellingRoutes.size());
        assertTrue(topSellingRoutes.contains("NYC - LAX"));
        assertTrue(topSellingRoutes.contains("SFO - ORD"));
    }
}
