package pojos;

public class PojoBookingResponseBody {
    private int bookingid;
    private PojoBookingBody booking;

    @Override
    public String toString() {
        return "PojoBookingResponseBody{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }

    public PojoBookingResponseBody() {
    }

    public PojoBookingResponseBody(int bookingid, PojoBookingBody booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public PojoBookingBody getBooking() {
        return booking;
    }

    public void setBooking(PojoBookingBody booking) {
        this.booking = booking;
    }
}
