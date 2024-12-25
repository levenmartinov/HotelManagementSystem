package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Reservation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReservationRepository {

    private Session session;

    public void save(Reservation reservation) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(reservation);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtils.closeSession(session);
        }

    }

    public Reservation findById(Long reservationId) {

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Reservation reservation = session.get(Reservation.class, reservationId);
            return reservation;

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            HibernateUtils.closeSession(session);
        }

        return null;
    }

    public List<Reservation> findAll() {

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            List<Reservation> reservations = session.createQuery("FROM Reservetion", Reservation.class).getResultList();
            return reservations;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtils.closeSession(session);
        }
    }


    public void delete(Reservation foundReservation) {

        try {

            session = HibernateUtils.getSessionFactory().openSession();
            Transaction t = session.beginTransaction();

            session.delete(foundReservation);

            t.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            HibernateUtils.closeSession(session);
        }

    }
}