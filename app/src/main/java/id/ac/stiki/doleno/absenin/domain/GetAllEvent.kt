package id.ac.stiki.doleno.absenin.domain;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Event;

public interface GetAllEvent {
    LiveData<List<Event>> execute();
}
