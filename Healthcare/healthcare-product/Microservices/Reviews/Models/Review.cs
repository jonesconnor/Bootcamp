using System.ComponentModel.DataAnnotations;
using Microsoft.EntityFrameworkCore;

namespace Reviews.Models
{
    public class Review 
    {
        [Key]
        public int Id { get; set; }
        public int reviewerId { get; set; }
        public int revieweeId { get; set; }
        [Range(1, 5)]
        public double rating { get; set; }
        public string comment { get; set; }

    }

}
