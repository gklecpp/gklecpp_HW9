package proxy;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SongServiceProxy implements SongService {

    private final SongService realService;

    private final Map<Integer, Song> idCache = new HashMap<>();
    private final Map<String, List<Song>> titleCache = new HashMap<>();
    private final Map<String, List<Song>> albumCache = new HashMap<>();

    public SongServiceProxy(SongService realService){
        this.realService = realService;
    }

    @Override
    public Song searchById(Integer songID){
        if (idCache.containsKey(songID)) {
            System.out.println("Proxy: Returning cached song by ID " + songID);
            return idCache.get(songID);
        }

        Song song = realService.searchById(songID);
        idCache.put(songID, song);
        return song;
    }

    @Override
    public List<Song> searchByTitle(String title){

        String key = title.toLowerCase();

        if (titleCache.containsKey(key)){
            System.out.println("Proxy: Returning cached song by title " + title);
            return titleCache.get(key);
        }

        List<Song> songs = realService.searchByTitle(title);
        titleCache.put(key, List.copyOf(songs));

        return titleCache.get(key);
    }

    @Override
    public List<Song> searchByAlbum(String album){

        String key = album.toLowerCase();

        if (albumCache.containsKey(key)){
            System.out.println("Proxy: Returning cached song by album " + album);
            return albumCache.get(key);
        }

        List<Song> songs = realService.searchByAlbum(album);
        albumCache.put(key, List.copyOf(songs));

        return albumCache.get(key);
    }

}
