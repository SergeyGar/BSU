import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


public class Train implements Serializable {
    String destinationName;
    int trainNumber;
    LocalDateTime timeDeparture;
    LocalDateTime timeArrival;

    public Train() {

    }

//    Calendar setDate(Date date){
//        Calendar calendar = new GregorianCalendar();
//        calendar.set(Calendar.YEAR, date.getYear());
//        calendar.set(Calendar.MONTH, date.getMonth());
//        calendar.set(Calendar.DAY_OF_MONTH, date.getDay());
//        calendar.set(Calendar.DAY_OF_WEEK, date.getDay());
//        calendar.set(Calendar.HOUR_OF_DAY, date.getHours());
//        calendar.set(Calendar.MINUTE, date.getMinutes());
//        return calendar;
//    }

    public Train(String line){
        String[] data = line.split("-");
        trainNumber = Integer.parseInt(data[0]);
        destinationName = data[1];
        timeDeparture = LocalDateTime.parse(data[2], DateTimeFormatter.ofPattern("H:m d.M.y", Locale.ENGLISH));
        timeArrival = LocalDateTime.parse(data[3], DateTimeFormatter.ofPattern("H:m d.M.y", Locale.ENGLISH));
    }

    public Train(int trainNumber, String destinationName, Date departureDate, Date arrivalDate){
        this.trainNumber = trainNumber;
        this.destinationName = destinationName;
        timeDeparture = LocalDateTime.of(departureDate.getYear(), departureDate.getMonth(),
                departureDate.getDay(), departureDate.getHours(), departureDate.getMinutes());
        timeArrival = LocalDateTime.of(arrivalDate.getYear(), arrivalDate.getMonth(),
                arrivalDate.getDay(), arrivalDate.getHours(), arrivalDate.getMinutes());
    }

    public Train( int numberTrain, String nameDestination, LocalDateTime timeDeparture, LocalDateTime timeArrival) {
        this.destinationName = nameDestination;
        this.trainNumber = numberTrain;
        this.timeDeparture = timeDeparture;
        this.timeArrival = timeArrival;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public LocalDateTime getTimeDeparture() {
        return timeDeparture;
    }

    public LocalDateTime getTimeArrival(){
        return timeArrival;
    }

    public String print() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.ENGLISH);
        Date departureDate = Date.from(timeDeparture.atZone(ZoneId.systemDefault()).toInstant());
        Date arrivalDate = Date.from(timeArrival.atZone(ZoneId.systemDefault()).toInstant());
        return ", Train number - " + trainNumber +
                "destination - '" + destinationName + '\'' +
                ", departure time - '" + simpleDateFormat.format(departureDate) + '\'' +
                ", arrival time - " + simpleDateFormat.format(arrivalDate) + '\'' + '\'';
    }

    @Override
    public String toString(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.ENGLISH);
        Date departureDate = Date.from(timeDeparture.atZone(ZoneId.systemDefault()).toInstant());
        Date arrivalDate = Date.from(timeArrival.atZone(ZoneId.systemDefault()).toInstant());
        return trainNumber + "-" + destinationName + "-" + simpleDateFormat.format(departureDate) +
                "-" + simpleDateFormat.format(arrivalDate);
    }
}
