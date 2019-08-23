package demo.microservice.moviecatalogservice.resources;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import demo.microservice.moviecatalogservice.model.CatalogItem;
import demo.microservice.moviecatalogservice.model.Movie;
import demo.microservice.moviecatalogservice.model.Rating;

@RestController
@RequestMapping("/catalog")
public class CatalogResource {
	
	@Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
    	List<Rating> ratingsList = Arrays.asList(
                new Rating("1234", 3),
                new Rating("5678", 4)
        );

        return ratingsList.stream()
        		.map(rating -> {
                    Movie movie = restTemplate.getForObject("http://localhost:8083/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), "Description", rating.getRating());
                })
                .collect(Collectors.toList());
    }
}
