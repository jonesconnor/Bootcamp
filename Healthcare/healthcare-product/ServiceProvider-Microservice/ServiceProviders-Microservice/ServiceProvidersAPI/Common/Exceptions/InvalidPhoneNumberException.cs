namespace ServiceProvidersAPI.Common.Exceptions
{
    public class InvalidPhoneNumberException : Exception
    {
        public InvalidPhoneNumberException() { }
        public InvalidPhoneNumberException(string message) : base(message) { }
    }
}
