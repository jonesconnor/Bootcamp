using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using ServiceProvidersAPI.Common.Exceptions;
using ServiceProvidersAPI.Models;
using ServiceProvidersAPI.Services;
using System.Text.RegularExpressions;

namespace ServiceProvidersAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ServiceProvidersController : ControllerBase
    {
        private readonly IServiceProviderService _service;
        public ServiceProvidersController(IServiceProviderService service)
        {
            _service = service;
        }

        // Create Service Provider
        // POST: /api/serviceproviders
        [Authorize(Roles = "Admin")]
        [HttpPost]
        public async Task<IActionResult> Create(Models.ServiceProvider newServiceProvider)
        {
            try
            {
                await _service.CreateAsync(newServiceProvider);
                return Ok(newServiceProvider);
            }
            catch (InvalidEmailException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (InvalidPhoneNumberException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (Exception)
            {
                return BadRequest("Unexpected error occurred");
            }
        }
        
        // Update Service Provider Info
        // PUT: /api/serviceproviders/{id}
        [Authorize(Roles = "Admin,ServiceProvider")]
        [HttpPatch("{id}")]
        public async Task<IActionResult> Update(string id, Models.ServiceProvider updatedServiceProvider)
        {
            try
            {
                var existingServiceProvider = await _service.GetByIdAsync(id);
                if (existingServiceProvider is null)
                {
                    return NotFound();
                }

                // Copy all fields that are not being passed in the PATCH request
                // so that they are not lost
                updatedServiceProvider.Id = existingServiceProvider.Id;
                if (updatedServiceProvider.AverageRating == 0)
                {
                    updatedServiceProvider.AverageRating = existingServiceProvider.AverageRating;
                }
                if (updatedServiceProvider.Bookings is null || updatedServiceProvider.Bookings.Count == 0)
                { 
                    updatedServiceProvider.Bookings = existingServiceProvider.Bookings; 
                }
                if (updatedServiceProvider.Notifications is null || updatedServiceProvider.Notifications.Count == 0)
                {
                    updatedServiceProvider.Notifications = existingServiceProvider.Notifications;
                }
                
                await _service.UpdateAsync(id, updatedServiceProvider);
                return NoContent();
            }
            catch (InvalidObjectIdException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (InvalidEmailException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (InvalidPhoneNumberException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (Exception)
            {
                return BadRequest("Unexpected error occurred");
            }
        }


        // Delete Service Provider
        // DELETE: /api/serviceproviders/{id}
        [Authorize(Roles = "Admin")]
        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(string id)
        {
            try
            {
                var existingServiceProvider = await _service.GetByIdAsync(id);
                if (existingServiceProvider is null)
                {
                    return NotFound();
                }

                await _service.RemoveAsync(id);
                return NoContent();
            }
            catch (InvalidObjectIdException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (Exception)
            {
                return BadRequest("Unexpected error occurred");
            }
        }

        // View all Doctors
        // GET: /api/serviceproviders/doctors
        [HttpGet("doctors")]
        public async Task<IActionResult> GetAllDoctors() =>
            Ok(await _service.GetAllByFilterAsync(x => x.Specialization!.ToLower() != "caretaker"));


        // View all Caretakers
        // GET: /api/ServiceProviders/caretakers
        [HttpGet("caretakers")]
        public async Task<IActionResult> GetAllCareTakers() =>
            Ok(await _service.GetAllByFilterAsync(x => x.Specialization!.ToLower() == "caretaker"));


        // View Service Provider By Id
        // GET: /api/ServiceProviders/{id}
        [HttpGet("{id}")]
        public async Task<IActionResult> GetById(string id)
        {
            try
            {
                var serviceProvider = await _service.GetByIdAsync(id);
                if (serviceProvider is null)
                {
                    return NotFound();
                }
                return Ok(serviceProvider);
            }
            catch(InvalidObjectIdException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (Exception)
            {
                return BadRequest("Unexpected error occurred");
            }
        }

        // View Service Provider by email
        [HttpGet("email/{emailAddress}")]
        public async Task<IActionResult> GetByEmail(string emailAddress)
        {
            try
            {
                if (!IsValidEmail(emailAddress))
                {
                    throw new InvalidEmailException("Invalid email format");
                }
                var serviceProvider = (await _service.GetAllByFilterAsync(x => x.Email == emailAddress)).FirstOrDefault();
                if (serviceProvider is null)
                {
                    return NotFound();
                }
                return Ok(serviceProvider);
            }
            catch (InvalidEmailException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (Exception)
            {
                return BadRequest("Unexpected error occurred");
            }
        }

        // View doctors by location
        // GET: /api/ServiceProviders/doctors/location/{location}
        [HttpGet("doctors/location/{location}")]
        public async Task<IActionResult> GetDoctorsByLocation(string location)
        {
            return Ok(await _service.GetAllByFilterAsync(x => x.Location.Contains(location) && x.Specialization!.ToLower() != "caretaker"));
        }

        // View Caretakers by location
        // GET: /api/ServiceProviders/caretakers/location/{location}
        [HttpGet("caretakers/location/{location}")]
        public async Task<IActionResult> GetCareTakersByLocation(string location)
        {
            return Ok(await _service.GetAllByFilterAsync(x => x.Location.Contains(location) && x.Specialization!.ToLower() == "caretaker"));
        }


        // View all Bookings for a provider
        // GET: /api/ServiceProviders/{id}/bookings
        [HttpGet("{id}/bookings")]
        public async Task<IActionResult> GetBookingsById(string id)
        {
            try
            {
                var existingServiceProvider = await _service.GetByIdAsync(id);
                if (existingServiceProvider is null)
                {
                    return NotFound();
                }
                return Ok(await _service.GetBookingsByIdAsync(id));
            }
            catch (InvalidObjectIdException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (Exception)
            {
                return BadRequest("Unexpected error occurred");
            }            
        }

        // View all Bookings for a provider for a specific date
        // GET: /api/ServiceProviders/{id}/bookings/{bookingDate}
        [HttpGet("{id}/bookings/{bookingDate}")]
        public async Task<IActionResult> GetBookingsByIdAndDate(string id, DateTime bookingDate)
        {
            try
            {
                var existingServiceProvider = await _service.GetByIdAsync(id);
                if (existingServiceProvider is null)
                {
                    return NotFound();
                }
                return Ok(await _service.GetBookingsByIdAndDateAsync(id, bookingDate));
            }
            catch (InvalidObjectIdException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (Exception)
            {
                return BadRequest("Unexpected error occurred");
            }
        }

        // Add New Booking For Provider
        // PATCH: /api/ServiceProviders/{id}/bookings
        [HttpPatch("{id}/bookings")]
        public async Task<IActionResult> AddNewBookingForProvider(string id, TimeSlot booking)
        {
            try
            {
                var existingServiceProvider = await _service.GetByIdAsync(id);
                if (existingServiceProvider is null)
                {
                    return NotFound();
                }

                if (existingServiceProvider.Bookings is null)
                {
                    existingServiceProvider.Bookings = new();
                }

                var existingBooking = existingServiceProvider.Bookings.FirstOrDefault(b => b.BookingDate == booking.BookingDate);
                
                if (existingBooking is not null) 
                {
                    return BadRequest("A booking already exists during this timeslot");
                }

                existingServiceProvider.Bookings.Add(booking);
                await _service.UpdateAsync(id, existingServiceProvider);
                return NoContent();
            }
            catch (InvalidObjectIdException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (Exception)
            {
                return BadRequest("Unexpected error occurred");
            }            
        }

        // Update an existing booking For provider
        // PATCH: /api/ServiceProviders/{id}/bookings/modify/{bookingId}
        [Authorize(Roles = "Admin,ServiceProvider")]
        [HttpPatch("{id}/bookings/modify/{bookingId}")]
        public async Task<IActionResult> UpdateBookingForProvider(string id, string bookingId, TimeSlot updatedBooking)
        {
            try
            {
                var existingServiceProvider = await _service.GetByIdAsync(id);
                if (existingServiceProvider is null || existingServiceProvider.Bookings is null)
                {
                    return NotFound();
                }

                var bookingFromDb = existingServiceProvider.Bookings.Find(x => x.BookingId == bookingId);
                if (bookingFromDb is null)
                {
                    return NotFound();
                }

                var existingBookings = existingServiceProvider.Bookings.Where(b => b.BookingDate == updatedBooking.BookingDate).ToList();

                if (existingBookings.Count > 1)
                {
                    return BadRequest("A booking already exists during this timeslot");
                }

                bookingFromDb.BookingDate = updatedBooking.BookingDate;
                bookingFromDb.Duration = updatedBooking.Duration;

                await _service.UpdateAsync(id, existingServiceProvider);
                return NoContent();
            }
            catch (InvalidObjectIdException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (Exception)
            {
                return BadRequest("Unexpected error occurred");
            }
        }

        // Delete an existing booking for provider
        // PATCH: 
        [Authorize(Roles = "Admin,ServiceProvider")]
        [HttpPatch("{id}/bookings/delete/{bookingId}")]
        public async Task<IActionResult> DeleteBookingForProvider(string id, string bookingId)
        {
            try
            {
                var existingServiceProvider = await _service.GetByIdAsync(id);
                if (existingServiceProvider is null || existingServiceProvider.Bookings is null)
                {
                    return NotFound();
                }

                var existingBooking = existingServiceProvider.Bookings.Find(x => x.BookingId == bookingId);
                if (existingBooking is null)
                {
                    return NotFound();
                }

                existingServiceProvider.Bookings.Remove(existingBooking);
                await _service.UpdateAsync(id, existingServiceProvider);
                return NoContent();
            }
            catch (InvalidObjectIdException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (Exception)
            {
                return BadRequest("Unexpected error occurred");
            }
        }

        // View all Notifications for a Provider
        // GET: /api/ServiceProviders/{id}/notifications
        [Authorize(Roles = "ServiceProvider")]
        [HttpGet("{id}/notifications")]
        public async Task<IActionResult> GetNotificationsForProviderById(string id)
        {
            try
            {
                var existingServiceProvider = await _service.GetByIdAsync(id);
                if (existingServiceProvider is null)
                {
                    return NotFound("A service provider with the given id does not exist");
                }
                return Ok(await _service.GetNotificationsByIdAsync(id));
            }
            catch (InvalidObjectIdException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (Exception)
            {
                return BadRequest("Unexpected error occurred");
            }            
        }

        // Add a new notification for provider
        // PATCH: /api/ServiceProviders/{id}/notifications
        [HttpPatch("{id}/notifications/{message}")]
        public async Task<IActionResult> AddNewNotificationForProvider(string id, string message)
        {
            try
            {
                var existingServiceProvider = await _service.GetByIdAsync(id);
                if (existingServiceProvider is null)
                {
                    return NotFound();
                }

                if (existingServiceProvider.Notifications is null)
                {
                    existingServiceProvider.Notifications = new();
                }

                // If the notifications list already has 5 notifications, remove the oldest notification
                if (existingServiceProvider.Notifications.Count == 5)
                {
                    existingServiceProvider.Notifications.RemoveAt(0);
                }
                existingServiceProvider.Notifications.Add(message);
                await _service.UpdateAsync(id, existingServiceProvider);
                return NoContent();
            }
            catch (InvalidObjectIdException ex)
            {
                return BadRequest(ex.Message);
            }
            catch (Exception)
            {
                return BadRequest("Unexpected error occurred");
            }
        }

        // Delete a notification with the given 'message' for provider
        // PATCH: /api/ServiceProviders/{id}/notifications/delete
        [Authorize(Roles = "ServiceProvider")]
        [HttpPatch("{id}/notifications/delete/{message}")]
        public async Task<IActionResult> DeleteNotificationForProvider(string id, string message)
        {
            try
            {
                var existingServiceProvider = await _service.GetByIdAsync(id);
                if (existingServiceProvider is null || existingServiceProvider.Notifications is null)
                {
                    return NotFound();
                }

                existingServiceProvider.Notifications.Remove(message);
                await _service.UpdateAsync(id, existingServiceProvider);
                return NoContent();
            }
            catch (InvalidObjectIdException ex)
            {
                return BadRequest(ex.Message);
            }            
            catch (Exception)
            {
                return BadRequest("Unexpected error occurred");
            }
        }


        // Checks if a given string is a valid email address
        [NonAction]
        public bool IsValidEmail(string email)
        {
            string regex = @"^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$";
            var match = Regex.Match(email, regex, RegexOptions.IgnoreCase);
            return match.Success;
        }
    }
}
