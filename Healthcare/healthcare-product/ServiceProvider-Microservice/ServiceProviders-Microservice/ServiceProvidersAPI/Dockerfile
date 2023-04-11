FROM mcr.microsoft.com/dotnet/aspnet:6.0 AS base
WORKDIR /app
EXPOSE 80

FROM mcr.microsoft.com/dotnet/sdk:6.0 AS build
WORKDIR /src
COPY ["ServiceProvidersAPI.csproj", "./"]
RUN dotnet restore "./ServiceProvidersAPI.csproj"
COPY . .
RUN dotnet build "ServiceProvidersAPI.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "ServiceProvidersAPI.csproj" -c Release -o /app/publish /p:UseAppHost=false

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "ServiceProvidersAPI.dll"]