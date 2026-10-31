package proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RealSongService implements SongService {
    private final Map<Integer, Song> songs = new HashMap<>();

    public RealSongService(){
        songs.put(1, new Song(1, "Espresso", "Sabrina Carpenter", "Short n' Sweet", 175));
        songs.put(2, new Song(2, "Snooze", "SZA", "SOS", 201));
        songs.put(3, new Song(3, "CHIHIRO", "Billie Eilish", "HIT ME HARD AND SOFT", 303));
        songs.put(4, new Song(4, "Sports car", "Tate Mcrae", "So Close to What", 165));
        songs.put(5, new Song(5, "Good Luck, Babe!", "Chappell Roan", "Good Luck, Babe!", 218));
        songs.put(6, new Song(6, "Come and See Me", "PARTYNEXTDOOR", "PARTYNEXTDOOR 3", 235));
        songs.put(7, new Song(7, "Blinding Lights", "The Weeknd", "After Hours", 200));
        songs.put(8, new Song(8, "we can't be friends (wait for your love)", "Ariana Grande", "eternal sunshine", 228));
    }

    private void simulateDelay(){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Song searchById(Integer songID){
        simulateDelay();

        System.out.println("Real server: Search by ID " + songID);

        return songs.get(songID);
    }

    @Override
    public List<Song> searchByTitle(String title){
        simulateDelay();

        System.out.println("Real server: Searching by title " + title);

        List<Song> results = new ArrayList<>();

        for(Song song : songs.values()){
            if(song.getTitle().equalsIgnoreCase(title)){
                results.add(song);
            }
        }
        return results;
    }

    @Override
    public List<Song> searchByAlbum(String album){
        simulateDelay();

        System.out.println("Real server: Searching by album " + album);

        List<Song> results = new ArrayList<>();

        for(Song song : songs.values()){
            if(song.getAlbum().equalsIgnoreCase(album)){
                results.add(song);
            }
        }
        return results;
    }
}
