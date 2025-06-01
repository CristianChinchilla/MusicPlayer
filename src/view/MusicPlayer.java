package view;

import java.util.*;
import controller.Playlist;

/**
 *
 * @author cchin
 */
public class MusicPlayer {

    public static void main(String[] args) throws InterruptedException {
        Playlist playlist = new Playlist();
        Scanner sc = new Scanner(System.in);
        String option;

        playlist.precarga();

        do {

            System.out.println("""
                           -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-
                           Ingrese la opción que desea:
                           
                           1. Ingresar una canción al final de la lista
                           2. Ingresar una canción al inicio de la lista
                           3. Iniciar la reproducción
                           4. Siguiente canción
                           5. Anterior canción
                           6. Eliminar canción
                           7. Mostar lista de canciones
                           8. Salir
                           -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-
                           """);

            option = sc.nextLine();

            switch (option) {

                case "1" -> {
                    String title;
                    String artist;
                    int lenght;

                    System.out.print("Ingrese el nombre de la canción: ");
                    title = sc.nextLine();
                    System.out.print("Ingrese el nombre del artista: ");
                    artist = sc.nextLine();
                    lenght = 6;

                    playlist.addLast(title, artist, lenght);
                }
                case "2" -> {

                    String title;
                    String artist;
                    int lenght;

                    System.out.print("Ingrese el nombre de la canción: ");
                    title = sc.nextLine();
                    System.out.print("Ingrese el nombre del artista: ");
                    artist = sc.nextLine();
                    lenght = 6;

                    playlist.addFirst(title, artist, lenght);
                }
                case "3" -> {
                    String setAutoplay;
                    boolean isAutoplayActive;

                    System.out.println("""
                                       Desea activar la reproducción automatica:
                                       
                                       1. Sí
                                       2. No
                                       """);

                    setAutoplay = sc.nextLine();

                    switch (setAutoplay) {
                        case "1" -> {
                            isAutoplayActive = true;
                            playlist.play(isAutoplayActive);
                        }
                        case "2" -> {
                            isAutoplayActive = false;
                            playlist.play(isAutoplayActive);
                        }
                        default -> {
                            System.out.println("Ingrese una opción valida.");
                        }
                    }
                }
                case "4" -> {
                    playlist.hasNext();
                }
                case "5" -> {
                    playlist.hasPrevious();
                }
                case "6" -> {
                    String songName;

                    System.out.println("Ingrese el nombre de la canción que desea eliminar:");
                    songName = sc.nextLine();

                    playlist.deleteSong(songName);
                }
                case "7" -> {
                    playlist.showPlaylist();
                }
            }
        } while (!"8".equals(option));
    }
}
