package id.ac.stiki.doleno.absenin.domain;

import id.ac.stiki.doleno.absenin.data.entity.Event;

public interface GetEventById {
    Event execute(int id);
}
