namespace PatientService.Exceptions
{
    public class PrescriptioinNotExistException : ApplicationException
    {
        public PrescriptioinNotExistException()
        {
            
        }

        public PrescriptioinNotExistException(string message) : base(message) { }
    }
}
