namespace PatientService.Exceptions
{
    public class PatientNotExistException : ApplicationException
    {
        public PatientNotExistException()
        {
            
        }

        public PatientNotExistException(string message) : base(message) { }
    }
}
