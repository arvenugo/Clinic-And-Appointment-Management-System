package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;


import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.exception.InvalidDataException;
import com.airtribe.meditrack.interfaces.AppointmentObserver;
import com.airtribe.meditrack.util.DateUtil;


import java.time.LocalDateTime;
import java.util.*;


public class AppointmentService {

    // Map to store appointments
    private Map<Integer, Appointment> appointmentMap = new HashMap<>();

    /**
     * List of appt observers
     */
    private List<AppointmentObserver> observers = new ArrayList<>();

    /**
     * Create Appointment
     */
    public Appointment createAppointment(Patient patient, Doctor doctor, LocalDateTime dateTime, String complaints) throws InvalidDataException {


        if (dateTime == null || DateUtil.validateFutureOrToday(dateTime)) {

            throw new InvalidDataException("Invalid appointment time");
        }


        Appointment appointment = new Appointment(doctor, patient,

                dateTime, complaints);


        appointmentMap.put(appointment.getApptId(), appointment);
        doctor.addAppointment(appointment);
        patient.addAppointment(appointment);
        notifyObservers("Appointment created", appointment);
        return appointment;
    }

    /**
     * Get Appointment by ID
     */
    public Appointment getAppointmentById(int id) throws AppointmentNotFoundException {
        Appointment appt = appointmentMap.get(id);

        if (appt == null) {
            throw new AppointmentNotFoundException("Appointment not found with id: " + id);
        }

        return appt;
    }

    /**
     * View all the appointments
     */
    public List<Appointment> getAllAppointments() {

        Comparator<Appointment> dateComparator = Comparator.comparing(Appointment::getApptTime);
        List<Appointment> apptList = new ArrayList<>(appointmentMap.values());
        apptList.sort(dateComparator);
        return apptList;
    }

    /**
     * Cancel Appointment
     */
    public void cancelAppointment(int id) throws AppointmentNotFoundException {
        if (!appointmentMap.containsKey(id)) {
            throw new AppointmentNotFoundException("Cannot delete. Appointment not found: " + id);
        }
        Appointment appt = getAppointmentById(id);
        appt.getDoctor().removeAppointment(appt);
        notifyObservers("Appointment cancelled", appt);
        appt.setApptStatus(AppointmentStatus.CANCELLED);
    }


    /**
     * Update Appointment Time
     */
    public void rescheduleAppointment(int id, LocalDateTime newDateTime) throws InvalidDataException {
        Appointment appt = getAppointmentById(id);

        if (newDateTime == null || DateUtil.validateFutureOrToday(newDateTime)) {
            throw new InvalidDataException("Invalid appointment time");
        }
        notifyObservers("Appointment updated", appt);
        appt.updateApptTime(newDateTime);
    }

    /**
     * Register an observer for appt
     *
     * @param observer
     */
    public void registerObserver(AppointmentObserver observer) {
        observers.add(observer);
    }

    /**
     * Notify the observer
     *
     * @param message
     * @param appointment
     */
    public void notifyObservers(String message, Appointment appointment) {
        for (AppointmentObserver observer : observers) {
            observer.update(message, appointment);
        }
    }

}