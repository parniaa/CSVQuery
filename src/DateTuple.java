import java.util.Date;

public class DateTuple {
    private Date minDate;

    @Override
    public String toString() {
        return "DateTuple{" +
                "minDate=" + minDate +
                ", maxDate=" + maxDate +
                '}';
    }

    private Date maxDate;

    public DateTuple(Date minDate, Date maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    public Date getMinDate() {
        return minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }
}
