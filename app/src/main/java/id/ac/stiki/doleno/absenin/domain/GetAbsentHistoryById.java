package id.ac.stiki.doleno.absenin.domain;

import id.ac.stiki.doleno.absenin.data.entity.Absent;

public interface GetAbsentHistoryById {
    Absent execute(int id);
}
