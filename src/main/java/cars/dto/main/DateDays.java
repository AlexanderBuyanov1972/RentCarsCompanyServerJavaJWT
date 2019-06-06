package cars.dto.main;

import java.time.LocalDate;

public class DateDays {
    public LocalDate date;
    public int days;

    public DateDays() {
    }
    // *********Getters*************
    public LocalDate getDate() {
        return date;
    }

    public int getDays() {
        return days;
    }

    // *********Setters*************

    public DateDays setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public DateDays setDays(int days) {
        this.days = days;
        return this;
    }
}
