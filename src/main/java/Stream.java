import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Stream {

    private final int streamType;
    private final int id;
    private final int streamGenre;
    private long noOfStreams;
    private final int streamerId;
    private final long length;
    private long dateAdded;
    private final String name;

    Stream (int streamType, int id, int streamGenre, long noOfStreams,
             int streamerId, long length, long dateAdded, String name) {

        this.streamType = streamType;
        this.id = id;
        this.streamGenre = streamGenre;
        this.noOfStreams = noOfStreams;
        this.streamerId = streamerId;
        this.length = length;
        this.dateAdded = dateAdded;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setNoOfStreams (long noOfStreams) {
        this.noOfStreams = noOfStreams;
    }

    public long getNoOfStreams() {
        return noOfStreams;
    }

    public String getName() {
        return name;
    }

    public int getStreamerId() {
        return streamerId;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getStreamType() {
        return streamType;
    }

    @Override
    public String toString () {

        AccountManager accountManager = AccountManager.getInstance();

        /* getting the stream's streamer name, it is obvious that it will be */
        /* of type Streamer */
        Account streamer = accountManager.getAccount(this.getStreamerId());

        String duration = getDuration(length);
        String streamDate = getStreamDate();

        return "{" + '"' + "id" + '"' + ':' + '"' + id + '"' + ',' +
                '"' + "name" + '"' + ':' + '"' + name + '"' + ',' +
                '"' + "streamerName" + '"' + ':' + '"' + streamer.getName() + '"' + ',' +
                '"' + "noOfListenings" + '"' + ':' + '"' + noOfStreams + '"' + ',' +
                '"' + "length" + '"' + ':' + '"' + duration + '"' + ',' +
                '"' + "dateAdded" + '"' + ':' + '"' + streamDate + '"' + '}';
    }

    /* sets the date to the desired format */
    private String getStreamDate() {

        Instant instant = Instant.ofEpochMilli(this.dateAdded * 1000);
        LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneId.of("Europe/London"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return formatter.format(date);
    }

    /* sets the duration to the desired format */
    private String getDuration (long length) {

        /* getting the hours, minutes and seconds */
        Duration duration = Duration.of(length, ChronoUnit.SECONDS);
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        long seconds = duration.minusHours(hours).minusMinutes(minutes).getSeconds();

        /* adding "0" if the seconds consist of only one digit */
        String sec = Long.toString(seconds);
        if(seconds < 10)
            sec = "0" + sec;

        /* adding "0" if the minutes consist of only one digit */
        String min = Long.toString(minutes);
        if(minutes < 10)
            min = "0" + min;

        /* adding "0" if the hours consist of only one digit */
        String h = Long.toString(hours);
        if(hours < 10)
            h = "0" + h;

        /* if the hours are equal to zero, it does not print them */
        if(hours == 0)
            return min + ":" + sec;

        return h + ":" + min + ":" + sec;
    }
}
