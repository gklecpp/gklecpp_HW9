package proxy;

import java.util.List;

public class ProxyDriver {

    public static void main(String [] args){

        SongService realService = new RealSongService();
        SongService proxy = new SongServiceProxy(realService);

        // search by song ID

        System.out.println("=== Search by ID ===");
        measureSearch(() -> System.out.println(proxy.searchById(1)));
        measureSearch(() -> System.out.println(proxy.searchById(1)));

        //search by song title
        System.out.println("\n=== Search by Title ===");
        measureSearch(() -> System.out.println(proxy.searchByTitle("Sports car")));
        measureSearch(() -> System.out.println(proxy.searchByTitle("Sports car")));

        //search by album
        System.out.println("\n=== Search by Album ===");
        measureSearch(() -> System.out.println(proxy.searchByAlbum("So Close to What")));
        measureSearch(() -> System.out.println(proxy.searchByAlbum("So Close to What")));

        // search for additional songs
        System.out.println("\n=== Additional Song Searches ===");

        printSongs(proxy.searchByTitle("Espresso"));
        printSongs(proxy.searchByTitle("Snooze"));
        printSongs(proxy.searchByTitle("CHIHIRO"));
        printSongs(proxy.searchByTitle("Good Luck, Babe!"));
        printSongs(proxy.searchByTitle("Come and See Me"));
        printSongs(proxy.searchByTitle("Blinding Lights"));
        printSongs(proxy.searchByTitle("we can't be friends (wait for your love)"));
    }

    private static void printSongs(List<Song> songs){
        if (songs.isEmpty()){
            System.out.println("No songs found.");
        } else {
            for (Song song : songs){
                System.out.println(song);
            }
        }
    }

    private static void measureSearch(Runnable search){
        long start = System.nanoTime();

        search.run();

        long end = System.nanoTime();
        double elapsedTime = (end - start) / 1_000_000.0;

        System.out.printf("Search time: %.2f ms%n%n", elapsedTime);
    }
}
