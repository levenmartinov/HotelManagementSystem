package com.tpe.service;

import com.tpe.domain.Guest;
import com.tpe.domain.Reservation;
import com.tpe.domain.Room;
import com.tpe.exceptions.ReservationNotFoundException;
import com.tpe.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReservationService {


    private Scanner scanner = new Scanner(System.in);

    private final ReservationRepository reservationRepository;

    private final RoomService roomService;

    private final GuestService guestService;

    public ReservationService(ReservationRepository reservationRepository, RoomService roomService, GuestService guestService) {
        this.reservationRepository = reservationRepository;
        this.roomService = roomService;
        this.guestService = guestService;
    }

    //10-b
    public void createReservation() {

        Reservation reservation = new Reservation();

        System.out.println("Enter check-in date (yyyy-MM-dd):");
        String checkin = scanner.nextLine();
        reservation.setCheckInDate(LocalDate.parse(checkin));

        System.out.println("Enter check-out date (yyyy-MM-dd):");
        String checkout = scanner.nextLine();
        reservation.setCheckOutDate(LocalDate.parse(checkout));

        System.out.println("Enter room id : ");
        Long roomId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter guest id");
        Long guestId = scanner.nextLong();
        scanner.nextLine();

        Room room = roomService.findRoomById(roomId);
        Guest guest = guestService.findGuestById(guestId);

        if (room != null && guest != null) {
            reservation.setRoom(room);//objeler arasındaki ilişki kuruldu
            reservation.setGuest(guest);

            reservationRepository.save(reservation);
            System.out.println("Reservation is created successfully...");
        } else {
            System.out.println("Reservation is CANCELLED!!!");
        }

    }

    public Reservation findReservationById(Long reservationId) {

        try {
            Reservation foundReservation = reservationRepository.findById(reservationId);
            if (foundReservation != null) {
                System.out.println("*----------------------*");
                System.out.println(foundReservation);
                System.out.println("*----------------------*");
                return foundReservation;
            } else {
                throw new ReservationNotFoundException("Reservation not found with ID : " + reservationId);
            }

        } catch (ReservationNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void getAllReservation() {

        List<Reservation> reservations = reservationRepository.findAll();
        if (!reservations.isEmpty()) {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        } else {
            System.out.println("No reservetion found!");
        }

    }


    public void deleteReservationById(Long reservetionid) {

        Reservation foundReservation = findReservationById(reservetionid);

        if (foundReservation != null) {

            System.out.println(foundReservation);
            System.out.println("Are you sure to delete : ");
            System.out.println("Please answer with Y or N : ");
            String select = scanner.next();

            if (select.equalsIgnoreCase("Y")) {
                reservationRepository.delete(foundReservation);
                System.out.println("Reservation os deleted...");
            } else {
                System.out.println("Delete operation is CANCELLED!!!");
            }

        } else {
            System.out.println("Delete operation is CANCELLED!!!");
        }

    }
}