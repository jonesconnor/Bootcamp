using Microsoft.AspNetCore.Mvc;
using Microsoft.CodeAnalysis.Host.Mef;
using Moq;

using Reviews.Controllers;
using Reviews.Models;
using Reviews.Repository;
using Xunit;


namespace test
{
    public class ReviewContollerTest 
    {
        [Fact]
        public async void GetShouldReturnListOfReviews()
        {

            var mockReview = new Mock<IReviewRepository>();
            var reviews = getReviews();
            mockReview.Setup(review => review.ViewAllReviews()).Returns(reviews);
            var reviewController = new ReviewsController(mockReview.Object);

            var actual = await reviewController.GetReviews();

            Assert.IsType<ActionResult<IEnumerable<Review>>>(actual);
        }

        [Fact]
        public async void CreateShouldReturnListOfReviews_ModelIsValid()
        {
            var review = new Review { Id = 1, reviewerId = 1, revieweeId = 1, rating = 1, comment = "First" };
            var mockReview = new Mock<IReviewRepository>();
            mockReview.Setup(repo => repo.CreateReview(review));
            var reviewController = new ReviewsController(mockReview.Object);

            var result = await reviewController.PostReview(review);
            Assert.IsType<OkObjectResult>(result);
        }

        [Fact]
        public async void DeleteShouldReturnNoContent()
        {
            int noteId = 1;
            var mockReview = new Mock<IReviewRepository>();
            mockReview.Setup(repo => repo.DeleteReview(noteId));
            var noteController = new ReviewsController(mockReview.Object);
            var result = await noteController.DeleteReview(noteId);
            Assert.IsType<NoContentResult>(result);
        }

        private async Task<List<Review>> getReviews()
        {
             List<Review> reviews = new List<Review>
            {
            new Review { Id = 1, reviewerId=1, revieweeId=1, rating = 1, comment ="one" },
            new Review { Id = 1, reviewerId=1, revieweeId=1, rating = 1, comment ="one" }
            };

            return reviews;
    }

       
    }
}