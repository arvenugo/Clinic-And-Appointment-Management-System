package com.airtribe.meditrack.interfaces;

import com.airtribe.meditrack.entity.Appointment;

/**
 * Implementation of Observer pattern - to be implemented to receive appt change notifications
 */
public interface AppointmentObserver {

    public void update(String message, Appointment appointment);
}
