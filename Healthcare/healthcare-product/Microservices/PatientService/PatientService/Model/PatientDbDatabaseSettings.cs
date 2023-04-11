using Microsoft.AspNetCore.Mvc.Rendering;

namespace PatientService.Model
{
    public class PatientDbDatabaseSettings
    {
        public string ConnectionString { get; set; } = null;
        public string DatabaseName { get; set; } = null;
        public string PatientsCollectionName { get; set; } = null;
    }
}
