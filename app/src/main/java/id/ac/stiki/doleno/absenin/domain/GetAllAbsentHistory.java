package id.ac.stiki.doleno.absenin.domain;

import java.util.List;

import id.ac.stiki.doleno.absenin.data.entity.Absent;

public interface GetAllAbsentHistory {
    List<Absent> execute();
}
