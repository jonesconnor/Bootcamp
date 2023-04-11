using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace ServiceProvidersAPI.Models
{
    public class ServiceProvider
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string? Id { get; set; }

        [BsonElement("firstName")]
        public string FirstName { get; set; } = null!;

        [BsonElement("lastName")]
        public string LastName { get; set; } = null!;

        [BsonElement("location")]
        public string Location { get; set; } = null!;

        [BsonElement("email")]
        public string Email { get; set; } = null!;

        [BsonElement("phoneNumber")]
        public string PhoneNumber { get; set; } = null!;

        [BsonElement("specialization")]
        public string? Specialization { get; set; }

        [BsonElement("fees")]
        public double Fee { get; set; }

        [BsonElement("averageRating")]
        public double AverageRating { get; set; }

        [BsonElement("bookings")]
        public List<TimeSlot>? Bookings { get; set; } = new List<TimeSlot>();

        [BsonElement("notifications")]
        public List<string>? Notifications { get; set; } = new List<string>();
    }
}
