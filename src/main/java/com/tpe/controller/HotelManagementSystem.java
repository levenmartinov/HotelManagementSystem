package com.tpe.controller;

import com.tpe.config.HibernateUtils;
import com.tpe.repository.GuestRepository;
import com.tpe.repository.HotelRepository;
import com.tpe.repository.ReservationRepository;
import com.tpe.repository.RoomRepository;
import com.tpe.service.GuestService;
import com.tpe.service.HotelService;
import com.tpe.service.ReservationService;
import com.tpe.service.RoomService;

import java.util.Scanner;

public class HotelManagementSystem {

    private static Scanner scanner = new Scanner(System.in);

    //ana menü kullanıcıya gösterilir ve seçimi alınır
    public static void displayHotelManagementSystemMenu() {
        //NOT:sadece 1'er tane service ve repo objeleri oluşturulur ve tüm uygulamada kullanılır.
        HotelRepository hotelRepository = new HotelRepository();
        HotelService hotelService = new HotelService(hotelRepository);

        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(roomRepository, hotelService);

        GuestRepository guestRepository = new GuestRepository();
        GuestService guestService = new GuestService(guestRepository);

        ReservationRepository reservationRepository = new ReservationRepository();
        ReservationService reservationService = new ReservationService(reservationRepository, roomService, guestService);

        int choice;

        do {

            System.out.println("========== Hotel Management System ==========");
            System.out.println("1.Hotel Operations");
            System.out.println("2.Room Operations");
            System.out.println("3.Guest Operations");
            System.out.println("4.Reservation Operations");
            System.out.println("0.Exit");
            System.out.print("Enter your choice : ");
            choice = scanner.nextInt();// \n
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayHotelOperationsMenu(hotelService);
                    break;
                case 2:
                    displayRoomOperationsMenu(roomService);
                    break;
                case 3:
                    displayGuestOperationsMenu(guestService);
                    break;
                case 4:
                    displayReservationOperationsMenu(reservationService);
                    break;
                case 0:
                    System.out.println("Good Bye...");
                    HibernateUtils.shutDown();
                    break;
                default:
                    System.out.println("Invalid choice, please try again!");
                    break;
            }
        } while (choice != 0);


    }


    //her bir kategori için ayrı metodlar oluşturalım:işlemleri gösteren ve seçimini alan
//hotel operations
    private static void displayHotelOperationsMenu(HotelService hotelService) {

        //HotelService hotelService=new HotelService();

        System.out.println("Hotel Operation Menu");

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Hotel Operations ====");
            System.out.println("1. Add a new hotel");
            System.out.println("2. Find Hotel By ID");
            System.out.println("3. Delete Hotel By ID");
            System.out.println("4. Find All Hotels");
            System.out.println("5. Update Hotel By ID");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //1-a:oteli kaydetme
                    hotelService.saveHotel();
                    break;
                case 2:
                    //2-a:hotel bulma
                    System.out.println("Enter hotel ID: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();

                    hotelService.findHotelById(id);
                    break;
                case 3:
                    //7-a
                    System.out.println("Enter hotel ID: ");
                    Long hotelid = scanner.nextLong();
                    scanner.nextLine();

                    hotelService.deleteHotelById(hotelid);
                    break;
                case 4:
                    //3-a:tüm otelleri listeleme
                    hotelService.getAllHotels();
                    break;
                case 5:
                    //8-a
                    System.out.println("Enter hotel ID: ");
                    Long updatedHotelid = scanner.nextLong();
                    scanner.nextLine();

                    hotelService.updateHotelById(updatedHotelid);
                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }

    //room operations
    private static void displayRoomOperationsMenu(RoomService roomService) {

        //RoomService roomService1=new RoomService();
        System.out.println("Room Operation Menu");
        boolean exit = false;
        while (!exit) {
            System.out.println("==== Room Operations ====");
            System.out.println("1. Add a new room");
            System.out.println("2. Find Room By ID");
            System.out.println("3. Delete Room By ID");
            System.out.println("4. Find All Rooms");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //4-a:bir odayı oluşturma
                    roomService.saveRoom();
                    break;
                case 2:
                    //5-a:ÖDEV
                    System.out.println("Enter room ID : ");
                    Long roomId = scanner.nextLong();
                    scanner.nextLine();

                    roomService.findRoomById(roomId);

                    break;
                case 3:
                    //ÖDEV1:id si verien odayı silme
                    System.out.println("Enter room ID: ");
                    Long roomid = scanner.nextLong();
                    scanner.nextLine();

                    roomService.deleteRoomById(roomid);
                    break;
                case 4:
                    //6-a:ÖDEV
                    roomService.getAllRooms();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");

                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }

    //guest operations
    private static void displayGuestOperationsMenu(GuestService guestService) {
        System.out.println("Guest Operation Menu");

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Guest Operations ====");
            System.out.println("1. Add a new guest");
            System.out.println("2. Find Guest By ID");
            System.out.println("3. Delete Guest By ID");
            System.out.println("4. Find All Guests");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //9-a
                    guestService.saveGuest();

                    break;
                case 2:
                    //ÖDEV2:guesti bulma
                    System.out.println("Enter guest ID : ");
                    Long guestId = scanner.nextLong();
                    scanner.nextLine();

                    guestService.findGuestById(guestId);

                    break;
                case 3:
                    //ÖDEV3:guesti silme
                    System.out.println("Enter guest ID : ");
                    Long guestid = scanner.nextLong();
                    scanner.nextLine();

                    guestService.deleteGuestById(guestid);
                    break;
                case 4:
                    //ÖDEV 4:tüm konukları listeleme
                    guestService.getAllGuests();

                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:

                    break;
            }
        }
    }

    //reservation operations
    private static void displayReservationOperationsMenu(ReservationService reservationService) {
        System.out.println("Reservation Operation Menu");

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Reservation Operations ====");
            System.out.println("1. Add a new reservation");
            System.out.println("2. Find Reservation By ID");
            System.out.println("3. Find All Reservations");
            System.out.println("4. Delete Reservation By ID");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //10-a:yeni rezervasyon
                    reservationService.createReservation();

                    break;
                case 2:
                    //ÖDEV5:
                    System.out.println("Enter Reservation ID : ");
                    Long reservationId = scanner.nextLong();
                    scanner.nextLine();

                    reservationService.findReservationById(reservationId);
                    break;
                case 3:
                    //ÖDEV6:
                    reservationService.getAllReservation();

                    break;
                case 4:
                    //ÖDEV7:
                    System.out.println("Enter Reservation ID : ");
                    Long reservetionid = scanner.nextLong();
                    scanner.nextLine();

                    reservationService.deleteReservationById(reservetionid);
                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");

                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }


    }


}