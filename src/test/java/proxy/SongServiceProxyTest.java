package proxy;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SongServiceProxyTest {

    @Test
    void testSearchById(){
        SongService proxy = new SongServiceProxy(new RealSongService());

        Song song = proxy.searchById(1);

        assertNotNull(song);
        assertEquals("Espresso", song.getTitle());
        assertEquals("Sabrina Carpenter", song.getArtist());
    }

    @Test
    void testSearchByTitle(){
        SongService proxy = new SongServiceProxy(new RealSongService());

        List<Song> songs = proxy.searchByTitle("Sports car");

        assertEquals(1, songs.size());
        assertEquals("Tate Mcrae", songs.get(0).getArtist());
    }

    @Test
    void testSearchByAlbum(){
        SongService proxy = new SongServiceProxy(new RealSongService());

        List<Song> songs = proxy.searchByAlbum("So Close to What");

        assertEquals(1, songs.size());
        assertEquals("Sports car", songs.get(0).getTitle());
    }

    @Test
    void testIdCaching(){

        SongService proxy = new SongServiceProxy(new RealSongService());

        Song first = proxy.searchById(1);
        Song second = proxy.searchById(1);

        assertSame(first, second);

    }

    @Test
    void testTitleCaching(){
        SongService proxy = new SongServiceProxy(new RealSongService());

        List<Song> first = proxy.searchByTitle("Sports car");
        List<Song> second = proxy.searchByTitle("Sports car");

        assertSame(first, second);
    }

    @Test
    void testAlbumCaching(){

        SongService proxy = new SongServiceProxy(new RealSongService());

        List<Song> first = proxy.searchByAlbum("Sports car");
        List<Song> second = proxy.searchByAlbum("Sports car");

        assertSame(first, second);
    }

    @Test
    void testSongNotFound(){
        SongService proxy = new SongServiceProxy(new RealSongService());

        Song song = proxy.searchById(999);

        assertNull(song);
    }

    @Test
    void testCaseSearch(){
        SongService proxy = new SongServiceProxy(new RealSongService());

        List<Song> songs = proxy.searchByTitle("SPORTS CAR");

        assertEquals(1, songs.size());
        assertEquals("Sports car", songs.get(0).getTitle());
    }
}
