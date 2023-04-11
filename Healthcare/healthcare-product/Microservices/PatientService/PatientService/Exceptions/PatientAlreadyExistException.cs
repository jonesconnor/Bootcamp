namespace PatientService.Exceptions
{
    public class PatientAlreadyExistException : ApplicationException
    {
        public PatientAlreadyExistException()
        {
            
        }

        public PatientAlreadyExistException(string message) : base(message) { }
        
    }
}
