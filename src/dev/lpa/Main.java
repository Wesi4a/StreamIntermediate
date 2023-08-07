package dev.lpa;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    /*
    The Java API designed the Stream to let you process data in a declarative way, much like a structured
    query language or SQL in a database.
    Again this lets you say what should happen,and not actually how it will happen.
     */
    public static void main(String[] args) {
        /*
        the dropWhile and takeWhile work well with ordered streams. If the stream isn't ordered,oracle tells us the
        result will be non-deterministic, meaning the results may differ upon subsequent executions.
         */
        IntStream.iterate((int) 'A' , i -> i <= (int) 'z', i-> i+1 )
                .filter(Character::isAlphabetic)
                .map(Character::toUpperCase)
                .distinct()
//                .skip(5)
//                .filter(i -> Character.toUpperCase(i) > 'E')
//                .dropWhile(i -> Character.toUpperCase(i)<= 'E') //I will drop any characters that are
                // less than or equal to a capital E
//                .takeWhile(i -> i <'a')//in this case I'll take elements while they are less than 'a'
                .forEach(d->System.out.printf("%c ",d));
        System.out.println();
        Random random = new Random();

        //without distinct I will see every letter generated in order
        Stream.generate(()->random.nextInt((int) 'A',(int)'Z' + 1))
                .limit(50)
                .distinct()
                .sorted()
                .forEach(d -> System.out.printf("%c ",d));

        System.out.println();
        int maxSeats = 100;
        int seatsInRow = 10;
        /*
        I can figure out the row's character, by using the integer on the stream, and dividing that by the seats in
        this row.For example, i divided by seats in row,will be 0 for the first 10seats,so they'll be in row A
         */

        var stream =
                Stream.iterate(0,i -> i < maxSeats,i -> i +1 )
                        .map(i->new Seat((char)('A' + i /seatsInRow),
                                i%seatsInRow + 1))
                        .skip(5)
                        .limit(10)
                        .peek(s -> System.out.println("--> " + s))
                        .sorted(Comparator.comparing(Seat::price)
                                .thenComparing(Seat::toString));

//                        .mapToDouble(Seat::price)
//                        .boxed()
//                        .map("%.2f"::formatted);
         stream.forEach(System.out::println);
    }
}
