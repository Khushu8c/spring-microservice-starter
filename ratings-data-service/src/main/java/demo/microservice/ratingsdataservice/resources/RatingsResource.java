package demo.microservice.ratingsdataservice.resources;

import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.microservice.ratingsdataservice.model.Rating;
import demo.microservice.ratingsdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

	@RequestMapping("/movies/{movieId}")
    public Rating getMovingRating(@PathVariable("movieId") String movieId) {
		LogManager.getLogger().info("Request to get rating details for movieId : {}", movieId);
		
        return new Rating(movieId, 4);
    }
	
	@RequestMapping("/user/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
		LogManager.getLogger().info("Request to get movie details for user id : {}", userId);
		
        UserRating userRating = new UserRating();
        userRating.initData(userId);
        return userRating;

    }
}