using MongoDB.Bson.Serialization.Attributes;
using MongoDB.Bson;
using System.ComponentModel.DataAnnotations;

namespace PatientService.Model
{
    public class Prescription
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string? Id { get; set; }
        public string Dosage { get; set; }
        public int Refills { get; set; }
        public string DoctorID { get; set; }
        public DateTime Expiration { get; set; }
    }
}
