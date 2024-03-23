package com.example.vacationpay.data;

import java.util.List;
import java.util.Map;

public class HolidaysData {
    public static final Map<String, List<Integer>> holidaysDays = Map.of(
            "JANUARY", List.of(1, 2, 3, 4, 5, 6, 7, 8),
            "FEBRUARY", List.of(23),
            "MARCH", List.of(8),
            "MAY", List.of(1, 9),
            "JUNE", List.of(12),
            "NOVEMBER", List.of(4)
    );

}
