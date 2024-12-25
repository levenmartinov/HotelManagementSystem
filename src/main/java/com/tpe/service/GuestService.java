package com.tpe.service;

import com.tpe.domain.Address;
import com.tpe.domain.Guest;
import com.tpe.exceptions.GuestNotFoundException;
import com.tpe.repository.GuestRepository;

import java.util.List;
import java.util.Scanner;

public class GuestService {

    private Scanner scanner = new Scanner(System.in);

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    //9-b
    public void saveGuest() {
        Guest guest = new Guest();
        System.out.println("Enter guest name : ");
        guest.setName(scanner.nextLine());

        Address address = new Address();
        System.out.println("Enter street : ");
        address.setStreet(scanner.nextLine());

        System.out.println("Enter city : ");
        address.setCity(scanner.nextLine());

        System.out.println("Enter country : ");
        address.setCountry(scanner.nextLine());

        System.out.println("Enter zipcode : ");
        address.setZipcode(scanner.nextLine());

        guest.setAddress(address);

        guestRepository.save(guest);
        System.out.println("Guest is saved successfully....");
    }

    //ödev2:b
    public Guest findGuestById(Long guestId) {
        try {
            Guest foundGuest = guestRepository.findById(guestId);
            if (foundGuest != null) {
                System.out.println("*-----------------------------------*");
                System.out.println(foundGuest);
                System.out.println("*-----------------------------------*");
                return foundGuest;
            } else {
                throw new GuestNotFoundException("Guest not found by ID:" + guestId);
            }
        } catch (GuestNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public void deleteGuestById(Long guestid) {

        Guest foundGuest = findGuestById(guestid);

        if (foundGuest != null) {

            System.out.println(foundGuest);
            System.out.println("Are you sure to delete : ");
            System.out.println("Please answer Y or N : ");
            String select = scanner.next();

            if (select.equalsIgnoreCase("Y")) {
                guestRepository.delete(foundGuest);
                System.out.println("Guest is deleted...");
            } else {
                System.out.println("Delete operation is CANCELLED!!!");
            }
        } else {
            System.out.println("Delete operation is CANCELLED!!!");
        }

    }


    public void getAllGuests() {

        List<Guest> guests = guestRepository.findAll();

        if (!guests.isEmpty()) {
            for (Guest guest : guests) {
                System.out.println(guest);
            }
        } else {
            System.out.println("No guests found!");
        }


    }












}