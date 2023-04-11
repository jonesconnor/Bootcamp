using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using MongoDB.Bson;
using MongoDB.Bson.IO;
using PatientService.Exceptions;
using PatientService.Model;
using PatientService.Service;
using System.Runtime.InteropServices;

namespace PatientService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PatientsController : ControllerBase
    {
        private readonly IPatientsService _services;

        public PatientsController(IPatientsService patientsService)
        {
            _services = patientsService;
        }

        [HttpGet]
        [Authorize(Roles = "ServiceProvider,Admin")]
        public async Task<IActionResult> Get()
        {
            try
            {
                return Ok(await _services.GetAllAsync());
            }
            catch (Exception ex)
            {

                return BadRequest(ex.Message);
            }

        }

        [HttpGet("{id}")]
        [Authorize(Roles = "Patient,ServiceProvider,Admin")]
        public async Task<IActionResult> GetById(string id)
        {
            try
            {
                var result = await _services.GetAllAsyncByFilter(x => x.Id == id);
                return Ok(result.FirstOrDefault());

            }
            catch (PatientNotExistException ex)
            {
                return NotFound(ex.Message);
            }
            catch (Exception ex)
            {

                return BadRequest(ex.Message);
            }
        }

        [HttpGet("email/{emailId}")]
        [Authorize(Roles = "Patient,ServiceProvider,Admin")]
        public async Task<IActionResult> GetByEmail(string emailId)
        {
            try
            {
                var result = await _services.GetAllAsyncByFilter(x => x.Email == emailId);
                return Ok(result.FirstOrDefault());

            }
            catch (PatientNotExistException ex)
            {
                return NotFound(ex.Message);
            }
            catch (Exception ex)
            {

                return BadRequest(ex.Message);
            }
        }


        [HttpPost]
        public async Task<IActionResult> Post(Patient patient)
        {
            try
            {
                return Ok(await _services.CreateAsync(patient));
            }
            catch (PatientAlreadyExistException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (Exception ex)
            {

                return BadRequest(ex.Message);
            }

        }
        [HttpPut("{id}")]
        [Authorize(Roles = "Patient,Admin")]
        public async Task<IActionResult> Update(string id, Patient updatePatient)
        {
            try
            {
                updatePatient.Id = id;
                return Ok(await _services.UpdateAsync(id, updatePatient));
            }
            catch (PatientNotExistException ex)
            {
                return NotFound(ex.Message);
            }
            catch (Exception ex)
            {

                return BadRequest(ex.Message);
            }
        }

        [HttpDelete]
        [Authorize(Roles = "Patient,Admin")]
        public async Task<IActionResult> Delete(string id)
        {
            try
            {

                return Ok(await _services.RemoveAsync(id));
            }
            catch (PatientNotExistException ex)
            {
                return NotFound(ex.Message);
            }
            catch (Exception ex)
            {

                return BadRequest(ex.Message);
            }
        }

        [HttpGet("{id}/prescriptions")]
        public async Task<IActionResult> GetAllScriptioin(string id)
        {
            try
            {
                return Ok(await _services.ViewPrescription(id));
            }
            catch (PatientNotExistException ex)
            {
                return NotFound(ex.Message);
            }
            catch (Exception ex)
            {

                return BadRequest(ex.Message);
            }
        }

        [HttpPut("{id}/prescriptions")]
        [Authorize(Roles = "ServiceProvider, Admin")]
        public async Task<IActionResult> CreatePrescription(string id, Prescription prescription)
        {
            try
            {
                return Ok(await _services.AddPrescription(id, prescription));
            }
            catch (PatientNotExistException ex)
            {

                return NotFound(ex.Message);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPut("{id}/prescriptions/{prescriptionId}")]
        [Authorize(Roles = "ServiceProvider, Admin")]
        public async Task<IActionResult> UpdatePrescription(string id, string prescriptionId, Prescription prescription)
        {
            try
            {
                return Ok(await _services.UpdatePrescription(id, prescriptionId, prescription));
            }
            catch (PatientNotExistException ex)
            {

                return NotFound(ex.Message);
            }
            catch (PrescriptioinNotExistException ex)
            {
                return NotFound(ex.Message);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPut("{id}/prescriptions/delete/{prescriptionId}")]
        [Authorize(Roles = "ServiceProvider, Admin")]
        public async Task<IActionResult> DeletePrescription(string id, string prescriptionId)
        {
            try
            {
                return Ok(await _services.DeletePrescription(id, prescriptionId));
            }
            catch (PatientNotExistException ex)
            {

                return NotFound(ex.Message);
            }
            catch (PrescriptioinNotExistException ex)
            {
                return NotFound(ex.Message);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }
        [HttpGet("{id}/notes")]
        [Authorize(Roles = "Patient,ServiceProvider,Admin")]
        public async Task<IActionResult> GetAllNotes(string id)
        {
            try
            {
                return Ok(await _services.ViewNotes(id));
            }
            catch (PatientNotExistException ex)
            {
                return NotFound(ex.Message);
            }
            catch (Exception ex)
            {

                return BadRequest(ex.Message);
            }
        }
        [HttpPut("{id}/notes/{note}")]
        [Authorize(Roles = "ServiceProvider,Admin")]
        public async Task<IActionResult> CreateNote(string id, string note)
        {
            try
            {
                return Ok(await _services.AddNotes(id, note));
            }
            catch (PatientNotExistException ex)
            {

                return NotFound(ex.Message);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }

        [HttpPut("{id}/notes/delete/{note}")]
        [Authorize(Roles = "Patient,ServiceProvider,Admin")]
        public async Task<IActionResult> DeleteNote(string id, string note)
        {
            try
            {
                return Ok(await _services.DeleteNote(id, note));
            }
            catch (PatientNotExistException ex)
            {

                return NotFound(ex.Message);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }
        }


    }
}
