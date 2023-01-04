package id.ac.stiki.doleno.absenin.domain;

import com.google.android.gms.tasks.Task;

import id.ac.stiki.doleno.absenin.data.entity.Event;
import id.ac.stiki.doleno.absenin.data.entity.User;

public interface RegisterEvent {
    Task<Void> execute(Event event, User user);
}
