package com.example.imdb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.imdb.Entity.Movie;
import com.example.imdb.Entity.Review;
import com.example.imdb.Entity.User;
import com.example.imdb.exceptionhandling.MovieNotFoundException;
import com.example.imdb.exceptionhandling.ReviewNotFoundException;
import com.example.imdb.exceptionhandling.UserNotFoundException;
import com.example.imdb.repository.MovieRepository;
import com.example.imdb.repository.ReviewRepository;
import com.example.imdb.repository.UserRepository;
import com.example.imdb.requestdto.ReviewRequestDto;
import com.example.imdb.responsedto.ReviewResponseDto;
import com.example.imdb.utility.ResponseStructure;

@Service
public class ReviewService {

	@Autowired
	ReviewRepository mr;
	@Autowired
	UserRepository ur;

	@Autowired
	MovieRepository movr;

	private Review convertToReview(ReviewRequestDto reqRev, Review r) {

		r.setMessage(reqRev.getMessage());
		r.setRating(reqRev.getRating());
		return r;

	}

	private ReviewResponseDto convertToReviewResponse(Review r) {
		ReviewResponseDto resp = new ReviewResponseDto();
		resp.setMessage(r.getMessage());
		resp.setRating(r.getRating());
		return resp;

	}

	public ResponseEntity<ResponseStructure<String>>  insertReview(ReviewRequestDto reqDto, int userId, int movId)
			throws MovieNotFoundException, UserNotFoundException {

		Optional<User> optional = ur.findById(userId);
		if (optional.isPresent()) {

			Optional<Movie> optionalMovie = movr.findById(movId);
			Movie movie = optionalMovie.get();

			if (optionalMovie.isPresent()) {

				Review review = convertToReview(reqDto, new Review());

				review.setUserMap(optional.get());
				review.setMovieMap(movie);

				mr.save(review);
				
				float rating = mr.avgRating(movId);
				movie.setMovieRating(rating);

				movr.save(movie);
				
				
				ResponseStructure<String> rs = new ResponseStructure<>();
				rs.setStatusCode(HttpStatus.CREATED.value());
				rs.setMessage(" Review data saved successfully");
				rs.setData(" 1 Review ADDED  SUCCESSFULLY");

				return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.CREATED);

			} else
				throw new MovieNotFoundException("movie not found to add the review");
		}
		else
			throw new UserNotFoundException("user not found to add review");

	}

	public ResponseEntity<ResponseStructure<ReviewResponseDto>> findById(int id) throws ReviewNotFoundException {
		ResponseStructure<ReviewResponseDto> rs = new ResponseStructure<ReviewResponseDto>();
		Optional<Review> id2 = mr.findById(id);

		if (id2.isPresent()) {
			Review ca = id2.get();
			ReviewResponseDto dto = convertToReviewResponse(ca);
			rs.setStatusCode(HttpStatus.FOUND.value());
			rs.setMessage(" Review Fetched successfully");
			rs.setData(dto);
			return new ResponseEntity<ResponseStructure<ReviewResponseDto>>(rs, HttpStatus.FOUND);

		}

		else
			throw new ReviewNotFoundException(" Review not found");

	}

	public ResponseEntity<ResponseStructure<String>> updateReview(ReviewRequestDto reqDto, int reviewId)
			throws ReviewNotFoundException {

		Optional<Review> review = mr.findById(reviewId);

		if (review.isPresent()) {

			Review existing = review.get();
			Review updated = convertToReview(reqDto, existing);

		

			mr.save(updated);
			Movie movieMap = existing.getMovieMap();
			int movieId = movieMap.getMovieId();

			float i = mr.avgRating(movieId); // calculate avg of ratings

			movieMap.setMovieRating(i);// set average of ratings particular move

			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.FOUND.value());
			rs.setMessage(" Review data updated successfully");
			rs.setData(" REVIEW UPDATED SUCCESSFULLY");

			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.FOUND);
		}

		else
			throw new ReviewNotFoundException("review not found with this Id");

	}

	public ResponseEntity<ResponseStructure<String>> deleteReviewById(int id) throws ReviewNotFoundException {
		Optional<Review> id2 = mr.findById(id);

		if (id2.isPresent()) {
			mr.deleteById(id);
			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.FOUND.value());
			rs.setMessage(" Review data deleted successfully");
			rs.setData(" REVIEW DELETED SUCCESSFULLY");

			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.FOUND);
		}

		else
			throw new ReviewNotFoundException(" review not found with this Id");

	}

	public ResponseEntity<ResponseStructure<List<ReviewResponseDto>>> findByMovieId(int movieId)
			throws ReviewNotFoundException, MovieNotFoundException {
		ResponseStructure<List<ReviewResponseDto>> rs = new ResponseStructure<List<ReviewResponseDto>>();

		Optional<Movie> movie = movr.findById(movieId);

		if (movie.isPresent()) {

			List<Review> revList = mr.findByMovieId(movieId);

			if (!revList.isEmpty()) {

				List<ReviewResponseDto> respList = new ArrayList<>();

				for (Review r : revList) {

					ReviewResponseDto dto = convertToReviewResponse(r);
					dto.setReviewId(r.getReviewId());

					User useri = r.getUserMap();

//				  StringBuilder sb = new  StringBuilder();
//				  sb.append("/users/");
//				  sb.append( useri.getUserId());
//				  
					Map<String, String> o = new HashMap<>();

					o.put("user", "/users/" + useri.getUserId());

					dto.setOptions(o);

					respList.add(dto);

				}

				rs.setStatusCode(HttpStatus.FOUND.value());
				rs.setMessage(" Review Fetched successfully");
				rs.setData(respList);
				return new ResponseEntity<ResponseStructure<List<ReviewResponseDto>>>(rs, HttpStatus.FOUND);

			}

			else
				throw new ReviewNotFoundException(" Review not found by this id");
		}

		else
			throw new MovieNotFoundException("Movie with given Id not present");

	}

}
