namespace ServiceProvidersAPI.Common.Exceptions
{
    public class InvalidObjectIdException : Exception
    {
        public InvalidObjectIdException() { }
        public InvalidObjectIdException(string message) : base(message) { }
    }
}
