using Microsoft.Identity.Client;
using Reviews.Models;
using System.Linq.Expressions;
using System.Runtime.InteropServices;

namespace Reviews.Repository
{
    public interface IReviewRepository
    {
        public void CreateReview(Review review);
        public Task<List<Review>> ViewAllReviews ();
        public  Task<List<Review>> GetReviewsByFilter(Expression<Func<Review, bool>>? filter = null);
        public Task<Review> ViewOneReview(int reviewId);
        public void UpdateReview(int reviewId, Review review);
        public void DeleteReview(int reviewId);
    }
}
