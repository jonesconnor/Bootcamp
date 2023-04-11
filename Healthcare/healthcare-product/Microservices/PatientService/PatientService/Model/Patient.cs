using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;
using System.ComponentModel.DataAnnotations;

namespace PatientService.Model
{
    public class Patient
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string? Id { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public string PhoneNumber { get; set; }
        public string PhaemacyPhoneNumber { get; set; } = "";
        public string Phn { get; set; }
        public string Municipality { get; set; }
        public string Address { get; set; }
        public string PostalCode { get; set; }
        public List<Prescription> Prescriptions { get; set; } = new List<Prescription>();
        public List<string> Notifications { get; set; } = new List<string>();
    }
}
