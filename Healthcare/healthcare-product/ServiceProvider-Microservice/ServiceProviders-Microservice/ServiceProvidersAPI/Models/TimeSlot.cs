using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace ServiceProvidersAPI.Models
{
    public class TimeSlot 
    {
        [BsonElement("bookingId")]
        [BsonRepresentation(BsonType.ObjectId)]
        public string? BookingId { get; set; }
        
        [BsonElement("bookingDate")]
        public DateTime BookingDate { get; set; }

        [BsonElement("duration")]
        public int Duration { get; set; }
    }
}
