package persistence;

import model.VideoGame;

import java.util.ArrayList;
import java.util.List;

public class VideoGameRepository {

    private List<VideoGame> games =
            new ArrayList<>();

    public void save(VideoGame game){
        games.add(game);
    }

    public List<VideoGame> findAll(){
        return games;
    }

    public VideoGame findByTitle(String title){

        for(VideoGame game : games){

            if(game.getTitle()
                    .equalsIgnoreCase(title)){
                return game;
            }

        }

        return null;
    }

    public void delete(String title){

        games.removeIf(
                game -> game.getTitle()
                        .equalsIgnoreCase(title)
        );
    }
}
