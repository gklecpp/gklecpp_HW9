package proxy;

public class Song {
    private final int songID;
    private final String title;
    private final String artist;
    private final String album;
    private final int duration;

    public Song(int songID, String title, String artist, String album, int duration){
        this.songID = songID;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
    }

    public int getSongID(){
        return songID;
    }

    public String getTitle(){
        return title;
    }

    public String getArtist(){
        return artist;
    }

    public String getAlbum(){
        return album;
    }

    public int getDuration(){
        return duration;
    }

    @Override
    public String toString(){
        return "Song ID: " + songID + ", Title: " + title + ", Artist: " + artist + ", Album: " + album + ", Duration: " + duration + " seconds";
    }

}
