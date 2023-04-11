namespace PatientService.Exceptions
{
    public class InvalidEmailException : ApplicationException
    {
        public InvalidEmailException()
        {
            
        }
        public InvalidEmailException(string message) : base(message)
        {
            
        }
    }
}
