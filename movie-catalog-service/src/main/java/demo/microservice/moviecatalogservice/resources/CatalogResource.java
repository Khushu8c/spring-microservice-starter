package demo.microservice.moviecatalogservice.resources;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import demo.microservice.moviecatalogservice.model.CatalogItem;
import demo.microservice.moviecatalogservice.model.Movie;
import demo.microservice.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {
	
	@Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
    	LogManager.getLogger().info("Request to get catalog items for user : {}", userId);
    	
    	UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);

        return userRating.getRatings().stream()
        		.map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), "Description", rating.getRating());
                })
                .collect(Collectors.toList());
        
        /*
        Alternative WebClient way
        Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
        .retrieve().bodyToMono(Movie.class).block();
        */ 
    }
}
