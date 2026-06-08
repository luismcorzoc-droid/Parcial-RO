package service;

import model.*;
import persistence.VideoGameRepository;

import java.util.ArrayList;
import java.util.List;

public class VideoGameService {

    private VideoGameRepository repository =
            new VideoGameRepository();

    private List<Sale> sales =
            new ArrayList<>();

    public void addVideoGame(VideoGame game)
            throws Exception {

        if(game.getTitle() == null ||
                game.getTitle().isEmpty()) {

            throw new Exception("Titulo vacio");
        }

        if(repository.findByTitle(
                game.getTitle()) != null) {

            throw new Exception(
                    "El videojuego ya existe"
            );
        }

        repository.save(game);
    }

    public List<VideoGame> getAllGames() {
        return repository.findAll();
    }

    public VideoGame searchByTitle(String title) {
        return repository.findByTitle(title);
    }

    public void deleteGame(String title) {
        repository.delete(title);
    }

    public void updateGame(String oldTitle,
                           VideoGame newGame)
            throws Exception {

        deleteGame(oldTitle);
        addVideoGame(newGame);
    }

    public List<VideoGame> searchByPlatform(
            String platform) {

        List<VideoGame> encontrados =
                new ArrayList<>();

        for(VideoGame game :
                repository.findAll()) {

            if(game.getPlatform()
                    .equalsIgnoreCase(platform)) {

                encontrados.add(game);
            }
        }

        return encontrados;
    }

    public double sellGame(String title,
                           int quantity)
            throws Exception {

        VideoGame game =
                repository.findByTitle(title);

        if(game == null) {

            throw new Exception(
                    "Juego no encontrado"
            );
        }

        if(game.getStock() < quantity) {

            throw new Exception(
                    "Stock insuficiente"
            );
        }

        game.setStock(
                game.getStock() - quantity
        );

        double total =
                game.calculateFinalPrice()
                        * quantity;

        sales.add(
                new Sale(
                        title,
                        quantity,
                        total
                )
        );

        return total;
    }

    public List<Sale> getSales() {
        return sales;
    }
}