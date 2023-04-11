using PatientService.Model;
using System.Linq.Expressions;

namespace PatientService.Service
{
    public interface IPatientsService
    {
        Task<List<Patient>> GetAllAsync();
        Task<List<Patient>> GetAllAsyncByFilter(Expression<Func<Patient, bool>>? filter = null);
        Task<bool> CreateAsync(Patient patient);
        Task<bool> RemoveAsync(string id);
        Task<bool> UpdateAsync(string id, Patient patient);
        Task<List<Prescription>> ViewPrescription(string id);
        Task<bool> AddPrescription(string id, Prescription prescription);
        Task<bool> UpdatePrescription(string id, string prescriptionId, Prescription prescription);
        Task<bool> DeletePrescription(string id, string prescriptionId);
        Task<List<string>> ViewNotes(string id);
        Task<bool> AddNotes(string id, string note);
        Task<bool> DeleteNote(string id, string note);
    }
}
