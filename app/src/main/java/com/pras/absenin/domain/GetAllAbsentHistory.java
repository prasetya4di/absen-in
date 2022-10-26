package com.pras.absenin.domain;

import com.pras.absenin.data.entity.Absent;

import java.util.List;

public interface GetAllAbsentHistory {
    List<Absent> execute();
}
