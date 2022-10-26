package com.pras.absenin.domain;

import com.pras.absenin.data.entity.Absent;

public interface GetAbsentHistoryById {
    Absent execute(int id);
}
