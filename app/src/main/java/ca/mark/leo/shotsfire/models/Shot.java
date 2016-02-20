package ca.mark.leo.shotsfire.models;

import java.util.Date;

/**
 * Created by tsd010 on 2016-02-19.
 */
public class Shot {
    private String sender;
    private String victim;
    private Date time;
    private int likes;

    // FOR FIREBASE
    @SuppressWarnings("unused")
    private Shot() {
    }

    public Shot(String sender, String victim) {
        this.sender = sender;
        this.victim = victim;
        this.time = new Date();
        this.likes = 0;
    }

    public String getSender() {
        return sender;
    }

    public String getVictim() {
        return victim;
    }

    public Date getTime() {
        return time;
    }

    public int getLikes() {
        return likes;
    }

    @Override
    public String toString() {
        return "Shot{" +
                "sender='" + sender + '\'' +
                ", victim='" + victim + '\'' +
                ", time=" + time +
                ", likes=" + likes +
                '}';
    }
}
