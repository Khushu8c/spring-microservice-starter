package demo.microservice.movieinfoservice.resources;

import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.microservice.movieinfoservice.model.Movie;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
    	
    	LogManager.getLogger().info("Request to get movie details for id : {}", movieId);
    	
        return new Movie(movieId, "Name for ID " + movieId);

    }

}
