import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Stream {

    private final int streamType;
    private final int id;
    int streamGenre;
    long noOfStreams;
    int streamerId;
    long length;
    long dateAdded;
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

    public String getName() {
        return name;
    }

    public int getStreamerId() {
        return streamerId;
    }

    public int getStreamType() {
        return streamType;
    }


    public String toString (List<User> users, List<Streamer> streamers) {
        AccountManager accountManager = AccountManager.getInstance();
        accountManager.updateLists(users, streamers);
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

    private String getStreamDate() {

        Instant instant = Instant.ofEpochMilli(this.dateAdded * 1000 - 3600);
        LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return formatter.format(date);
    }

    private String getDuration (long length) {

        Duration duration = Duration.of(length, ChronoUnit.SECONDS);
        long hours = duration.toHours();
        long minutes = duration.minusHours(hours).toMinutes();
        long seconds = duration.minusHours(hours).minusMinutes(minutes).getSeconds();

        String sec = Long.toString(seconds);
        if(seconds < 10)
            sec = "0" + sec;

        String min = Long.toString(minutes);
        if(minutes < 10)
            min = "0" + min;

        String h = Long.toString(hours);
        if(hours < 10)
            h = "0" + h;


        if(hours == 0)
            return min + ":" + sec;

        return h + ":" + min + ":" + sec;
    }
}
