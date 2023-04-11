namespace PatientService.Exceptions
{
    public class InvalidPhoneException : ApplicationException
    {
        public InvalidPhoneException() { }
        public InvalidPhoneException(string message) : base(message)
        {
            
        }
    }
}
