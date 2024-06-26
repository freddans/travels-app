package com.freddan.travels.controllers;

import com.freddan.travels.VO.BookingItemResponseTemplateVO;
import com.freddan.travels.VO.ResponseTemplateVO;
import com.freddan.travels.entities.Booking;
import com.freddan.travels.entities.Trip;
import com.freddan.travels.services.BookingItemService;
import com.freddan.travels.services.BookingService;
import com.freddan.travels.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class CustomerController {

    private final BookingService bookingService;
    private final TripService tripService;
    private final BookingItemService bookingItemService;

    @Autowired
    public CustomerController(BookingService bookingService, TripService tripService, BookingItemService bookingItemService) {
        this.bookingService = bookingService;
        this.tripService = tripService;
        this.bookingItemService = bookingItemService;
    }

    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> availableTrips() {
        return ResponseEntity.ok(tripService.allAvailableTrips());
    }

    @PostMapping("/booktrip")
    public ResponseTemplateVO bookTrip(@RequestParam("customerId") long customerId, @RequestParam("tripId") long tripId) {
        return bookingService.createBooking(customerId, tripId);
    }

    @PutMapping("/updatetrip/{id}")
    public ResponseEntity<Booking> updateTrip(@PathVariable("id") long bookingId, @RequestParam("customerId") long customerId, @RequestParam("tripId") long tripId) {
        return ResponseEntity.ok(bookingService.updateBooking(bookingId, customerId, tripId));
    }

    @GetMapping("/mybookings")
    public List<BookingItemResponseTemplateVO> myBookingItems(@RequestParam("customerId") long customerId) {
        return bookingItemService.findMyBookingItems(customerId);
    }
}