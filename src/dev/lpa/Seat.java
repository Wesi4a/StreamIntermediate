package dev.lpa;

public record Seat(char rowMarker,int seatNumber,double price) {
    public Seat(char rowMarker, int seatNumber) {
        this(rowMarker, seatNumber,
                //setting rule price all seats will be 75 except the discounted one which will be 50
                rowMarker > 'C' && (seatNumber <= 2 || seatNumber >= 50) ? 50 : 75);
    }

    @Override
    public String toString() {
        /*
        the seatNumber will be in the format, of B 001,for example,so I'll print the row marker with a
        character specifier.That will be followed by the seat number with an integer specifier, with a width of
        3,but i want leading zeros,so I make that zero 3. Finally, the price has float identifier
         */
        return "%c%03d %.0f".formatted(rowMarker,seatNumber,price);
    }
}
