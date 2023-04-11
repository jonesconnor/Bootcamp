using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Options;
using MongoDB.Bson;
using MongoDB.Driver;
using PatientService.Exceptions;
using PatientService.Model;
using System.Linq.Expressions;
using System.Reflection.Metadata.Ecma335;
using System.Security.Cryptography;
using System.Text;
using System.Text.RegularExpressions;

namespace PatientService.Service
{
    public class PatientsService : IPatientsService
    {
        private readonly IMongoCollection<Patient> _patientsCollectioin;

        public PatientsService(IOptions<PatientDbDatabaseSettings> patientdbDatabaseSettings)
        {
            var mongoClient = new MongoClient(patientdbDatabaseSettings.Value.ConnectionString);
            var mongoDatabase = mongoClient.GetDatabase(patientdbDatabaseSettings.Value.DatabaseName);
            _patientsCollectioin = mongoDatabase.GetCollection<Patient>(patientdbDatabaseSettings.Value.PatientsCollectionName);
        }

        public async Task<List<Patient>> GetAllAsync()
        {
            return await _patientsCollectioin.Find(_ => true).ToListAsync();
        }

        public async Task<List<Patient>> GetAllAsyncByFilter(Expression<Func<Patient, bool>>? filter = null)
        {
            if (filter is null)
            {
                return await _patientsCollectioin.Find(_ => true).ToListAsync();
            }
            else
            {
                var result = await _patientsCollectioin.Find(filter).ToListAsync();
                if (result.Count > 0)
                {
                    return result;
                }
                else
                {
                    throw new PatientNotExistException("Patient with not found!");
                }
            }
        }

        public async Task<bool> CreateAsync(Patient patient)
        {
            string strRegex = @"^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}" +
         @"\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\" +
         @".)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$";
            Regex re = new Regex(strRegex);
            string noStrRegex = "^[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$";
            Regex noRe = new Regex(noStrRegex);

            if (_patientsCollectioin.Find(x => x.Email == patient.Email || x.Phn == sha256_hash(patient.Phn)).ToList().Count > 0)
            {
                throw new PatientAlreadyExistException("The Email or PHN already been used.");
            }
            else if(!re.IsMatch(patient.Email))
            {
                throw new InvalidEmailException("Invalid email provided");
            }
            else if (!noRe.IsMatch(patient.PhoneNumber) || (patient.PhaemacyPhoneNumber != "" && !noRe.IsMatch(patient.PhaemacyPhoneNumber)))
            {
                throw new InvalidPhoneException("Invalid patient phone number provided");
            }
            else
            {
                patient.Phn = sha256_hash(patient.Phn);
                await _patientsCollectioin.InsertOneAsync(patient);
                return true;
            }

        }

        public async Task<bool> RemoveAsync(string id)
        {
            if (_patientsCollectioin.Find(x => x.Id == id).ToList().Count == 0)
            {
                throw new PatientNotExistException($"The patient with id: {id} does not exist.");
            }
            else
            {
                await _patientsCollectioin.DeleteOneAsync(x => x.Id == id);
                return true;
            }



        }
        public async Task<bool> UpdateAsync(string id, Patient patient)
        {
            string strRegex = @"^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}" +
         @"\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\" +
         @".)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$";
            Regex re = new Regex(strRegex);
            string noStrRegex = "^[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$";
            Regex noRe = new Regex(noStrRegex);

            if (_patientsCollectioin.Find(x => x.Id == id).ToList().Count == 0)
            {
                throw new PatientNotExistException($"The patient with id: {id} does not exist.");
            }
            else if (!re.IsMatch(patient.Email))
            {
                throw new InvalidEmailException("Invalid email provided");
            }
            else if (!noRe.IsMatch(patient.PhoneNumber) || (patient.PhaemacyPhoneNumber != "" && !noRe.IsMatch(patient.PhaemacyPhoneNumber)))
            {
                throw new InvalidPhoneException("Invalid patient phone number provided");
            }
            else
            {
                await _patientsCollectioin.ReplaceOneAsync(x => x.Id == id, patient);
                return true;
            }
        }

        public async Task<List<Prescription>> ViewPrescription(string id)
        {
            var patient = await _patientsCollectioin.Find(x => x.Id == id).ToListAsync();
            if (patient.Count == 0)
            {
                throw new PatientNotExistException($"The patient with id: {id} does not exist.");
            }
            else
            {
                return patient[0].Prescriptions.ToList();
            }
        }

        public async Task<bool> AddPrescription(string id, Prescription prescription)
        {
            var patient = await _patientsCollectioin.Find(x => x.Id == id).ToListAsync();
            if (patient.Count == 0)
            {
                throw new PatientNotExistException($"The patient with id: {id} does not exist.");
            }
            else
            {
                prescription.Id = ObjectId.GenerateNewId().ToString();
                patient[0].Prescriptions.Add(prescription);
                await _patientsCollectioin.ReplaceOneAsync(x => x.Id == id, patient[0]);
                return true;
            }
        }

        public async Task<bool> UpdatePrescription(string id, string prescriptionId, Prescription prescription)
        {
            var patient = await _patientsCollectioin.Find(x => x.Id == id).ToListAsync();
            if (patient.Count == 0)
            {
                throw new PatientNotExistException($"The patient with id: {id} does not exist.");
            }
            else
            {
                int index = patient[0].Prescriptions.FindIndex(x => x.Id == prescriptionId);
                if (index == -1)
                {
                    throw new PrescriptioinNotExistException($"Prescription with ID: {prescriptionId} not exist.");
                }
                else
                {
                    prescription.Id = prescriptionId;
                    patient[0].Prescriptions[index] = prescription;
                    await _patientsCollectioin.ReplaceOneAsync(x => x.Id == id, patient[0]);
                    return true;
                }
            }

        }

        public async Task<bool> DeletePrescription(string id, string prescriptionId)
        {
            var patient = await _patientsCollectioin.Find(x => x.Id == id).ToListAsync();
            if (patient.Count == 0)
            {
                throw new PatientNotExistException($"The patient with id: {id} does not exist.");
            }
            else
            {
                Prescription prescription = patient[0].Prescriptions.Find(x => x.Id == prescriptionId);
                if (prescription is null)
                {
                    throw new PrescriptioinNotExistException($"Prescription with ID: {prescriptionId} not exist.");
                }
                else
                {
                    patient[0].Prescriptions.Remove(prescription);
                    await _patientsCollectioin.ReplaceOneAsync(x => x.Id == id, patient[0]);
                    return true;
                }
            }
        }

        public async Task<List<string>> ViewNotes(string id)
        {
            var patient = await _patientsCollectioin.Find(x => x.Id == id).ToListAsync();
            if (patient.Count == 0)
            {
                throw new PatientNotExistException($"The patient with id: {id} does not exist.");
            }
            else
            {
                return patient[0].Notifications.ToList();
            }
        }

        public async Task<bool> AddNotes(string id, string note)
        {
            var patient = await _patientsCollectioin.Find(x => x.Id == id).ToListAsync();
            if (patient.Count == 0)
            {
                throw new PatientNotExistException($"The patient with id: {id} does not exist.");
            }
            else
            {
                patient[0].Notifications.Add(note);
                await _patientsCollectioin.ReplaceOneAsync(x => x.Id == id, patient[0]);
                return true;
            }
        }

        public async Task<bool> DeleteNote(string id, string note)
        {
            var patient = await _patientsCollectioin.Find(x => x.Id == id).ToListAsync();
            if (patient.Count == 0)
            {
                throw new PatientNotExistException($"The patient with id: {id} does not exist.");
            }
            else
            {
                
                patient[0].Notifications.Remove(note);
                await _patientsCollectioin.ReplaceOneAsync(x => x.Id == id, patient[0]);
                return true;
                
            }
        }




        public string sha256_hash(string value)
        {
            StringBuilder Sb = new StringBuilder();

            using (var hash = SHA256.Create())
            {
                Encoding enc = Encoding.UTF8;
                byte[] result = hash.ComputeHash(enc.GetBytes(value));

                foreach (byte b in result)
                    Sb.Append(b.ToString("x2"));
            }

            return Sb.ToString();
        }


    }
}
