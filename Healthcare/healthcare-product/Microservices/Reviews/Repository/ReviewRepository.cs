using Microsoft.AspNetCore.Mvc;
using Microsoft.CodeAnalysis;
using Microsoft.EntityFrameworkCore;
using Reviews.Models;
using System.Linq.Expressions;

namespace Reviews.Repository
{
    public class ReviewRepository : IReviewRepository
    {
        private readonly ReviewsContext _reviewsContext;

        //public ReviewRepository()
        //{
        //    _reviewsContext = new ReviewsContext();
        //}
        public ReviewRepository(ReviewsContext _context)
        {
            _reviewsContext = _context;
        }
        public void CreateReview(Review review)
        {
            _reviewsContext.Review.Add(review);
            _reviewsContext.SaveChanges();
        }

        public void DeleteReview(int reviewId)
        {
            Review review = _reviewsContext.Review.Find(reviewId);
            if (review != null)
            {
                _reviewsContext.Review.Remove(review);
            }
            _reviewsContext.SaveChanges();
        }

        public async Task<List<Review>> GetReviewsByFilter(Expression<Func<Review, bool>>? filter = null)
        {
           if (filter == null)
           {
                return await _reviewsContext.Review.ToListAsync();
           }

           return await _reviewsContext.Review.Where(filter).ToListAsync();
        }

        public void UpdateReview(int reviewId, Review review)
        {
            Review found = _reviewsContext.Review.Find(reviewId);
            if (found != null)
            {
                found.revieweeId = review.revieweeId;
                found.reviewerId = review.reviewerId;
                found.comment = review.comment;
                found.rating = review.rating;
  
                _reviewsContext.SaveChanges();
            }
        }

        public async Task<List<Review>> ViewAllReviews()
        {
           return await _reviewsContext.Review.ToListAsync();  
        }

        public async Task<Review> ViewOneReview(int reviewId)
        {
            return await _reviewsContext.Review.FindAsync(reviewId);
        }
    }
}
