using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Reviews.Attributes;
using Reviews.Models;
using Reviews.Repository;

namespace Reviews.Controllers
{
    [Route("api/[controller]")]
    [ReviewsException]
    [ApiController]
    public class ReviewsController : ControllerBase
    {
        private readonly IReviewRepository _repo;

        public ReviewsController(IReviewRepository repo)
        {
            _repo = repo;
        }

        // GET: api/Reviews
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Review>>> GetReviews()
        {
            return await _repo.ViewAllReviews();
        }

        // GET: api/Reviews/5
        [HttpGet("reviewee/{id}")]
        public async Task<ActionResult<IEnumerable<Review>>> GetReviewee(int id)
        {
            return await _repo.GetReviewsByFilter(x=> x.revieweeId == id);

        }

        // GET: api/Reviews/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Review>> GetReview(int id)
        {
            return await _repo.ViewOneReview(id);
        }

        // PUT: api/Reviews/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutReview(int id, Review review)
        {
            if (id != review.Id)
            {
                return BadRequest();
            }
            _repo.UpdateReview(id, review);
            return NoContent();
        }

        // POST: api/Reviews
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<IActionResult> PostReview(Review review)
        {
            _repo.CreateReview(review);
            return Ok(review);
        }

        // DELETE: api/Reviews/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteReview(int id)
        {

             _repo.DeleteReview(id);
            return NoContent();
        }

    }
}
