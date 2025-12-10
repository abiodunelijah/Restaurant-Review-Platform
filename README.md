# Restaurant Review System

A Spring Boot-based restaurant review and discovery platform with Elasticsearch search capabilities and OAuth2 security integration.

## Overview

This application provides a comprehensive restaurant review system that allows users to search, discover, and review restaurants. It leverages Elasticsearch for powerful search capabilities and Keycloak for secure authentication and authorization.

## Features

- **Restaurant Management**: Create, update, and manage restaurant profiles with detailed information
- **Advanced Search**: Elasticsearch-powered search with support for text, location-based queries, and filters
- **Review System**: Users can leave reviews with ratings, photos, and text content
- **Geolocation Support**: Search restaurants by proximity using GeoPoint coordinates
- **Secure Authentication**: OAuth2/JWT-based security with Keycloak integration
- **Photo Management**: Upload and manage restaurant and review photos
- **Operating Hours**: Track and display restaurant operating hours for each day of the week

## Tech Stack

- **Java 25**
- **Spring Boot 3.5.8**
- **Spring Security** with OAuth2 Resource Server
- **Spring Data Elasticsearch**
- **Elasticsearch 8.12.0**
- **Keycloak 23.0** (Identity and Access Management)
- **Kibana 8.12.0** (Elasticsearch visualization)
- **Lombok** for reducing boilerplate code
- **MapStruct** for object mapping
- **Maven** for dependency management and build automation

## Architecture

### Data Model

The application uses the following core entities:

- **Restaurant**: Main entity containing restaurant information
  - Basic details (name, cuisine type, contact)
  - Address with full location details
  - GeoPoint for location-based searches
  - Operating hours for each day
  - Photos and reviews
  - Average rating
  - Creator information

- **Review**: User reviews with ratings and photos
- **Address**: Structured address information
- **OperatingHours**: Daily operating time ranges
- **Photo**: Image metadata with upload timestamps
- **User**: User profile information from Keycloak

### Security

The application implements stateless JWT-based authentication:
- All endpoints require authentication
- JWT tokens are validated against Keycloak
- Session management is disabled (stateless)
- CSRF protection is disabled for REST API

## Prerequisites

- Java 25 or higher
- Maven 3.9.11 or higher
- Docker and Docker Compose
- At least 2GB of available RAM for Elasticsearch

## Getting Started

### 1. Start Infrastructure Services

The project includes a Docker Compose configuration for Elasticsearch, Kibana, and Keycloak:

```bash
docker-compose up -d
```

This will start:
- **Elasticsearch**: http://localhost:9200
- **Kibana**: http://localhost:5601
- **Keycloak**: http://localhost:9090

### 2. Configure Keycloak

1. Access Keycloak admin console at http://localhost:9090
2. Login with credentials:
   - Username: `admin`
   - Password: `admin`
3. Create a new realm named `restaurant-review`
4. Create a client for the application
5. Configure users and roles as needed

### 3. Build the Application

```bash
./mvnw clean package
```

Or on Windows:
```cmd
mvnw.cmd clean package
```

### 4. Run the Application

```bash
./mvnw spring-boot:run
```

Or run the JAR directly:
```bash
java -jar target/restaurant-0.0.1-SNAPSHOT.jar
```

The application will start on the default port (typically 8080).

## Configuration

Key configuration properties in `application.properties`:

```properties
# Application name
spring.application.name=restaurant

# Elasticsearch connection
spring.elasticsearch.uris=http://localhost:9200

# OAuth2 JWT configuration
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:9090/realms/restaurant-review

# Storage location for uploaded files
app.storage.location=uploads
```

## Project Structure

```
restaurant/
├── src/
│   ├── main/
│   │   ├── java/com/coder2client/
│   │   │   ├── config/           # Configuration classes
│   │   │   ├── entities/         # Domain entities
│   │   │   ├── exceptions/       # Custom exceptions
│   │   │   ├── repositories/     # Data access layer
│   │   │   └── services/         # Business logic layer
│   │   └── resources/
│   │       └── application.properties
│   └── test/                     # Test classes
├── docker-compose.yml            # Docker services configuration
├── pom.xml                       # Maven configuration
└── README.md
```

## API Endpoints

All endpoints require JWT authentication. Include the JWT token in the Authorization header:

```
Authorization: Bearer <your-jwt-token>
```

### Example Endpoints (to be implemented)

- `GET /api/restaurants` - List all restaurants
- `GET /api/restaurants/{id}` - Get restaurant details
- `POST /api/restaurants` - Create a new restaurant
- `PUT /api/restaurants/{id}` - Update restaurant
- `DELETE /api/restaurants/{id}` - Delete restaurant
- `GET /api/restaurants/search` - Search restaurants
- `POST /api/restaurants/{id}/reviews` - Add a review
- `POST /api/restaurants/{id}/photos` - Upload photos

## Development

### Building with Maven

```bash
# Clean and build
./mvnw clean install

# Run tests
./mvnw test

# Package without tests
./mvnw clean package -DskipTests
```

### Lombok and MapStruct

The project uses Lombok for reducing boilerplate code and MapStruct for object mapping. The annotation processors are configured in the Maven compiler plugin to work together correctly.

## Storage Service

The application includes a file system-based storage service for managing uploaded photos. The default storage location is `uploads/` directory, which can be configured via `app.storage.location` property.

## Monitoring and Management

- **Kibana**: Access http://localhost:5601 to visualize Elasticsearch data
- **Keycloak Admin**: Access http://localhost:9090 to manage authentication

## Troubleshooting

### Elasticsearch Connection Issues

If the application cannot connect to Elasticsearch:
1. Verify Elasticsearch is running: `curl http://localhost:9200`
2. Check Docker logs: `docker-compose logs elasticsearch`
3. Ensure ports 9200 and 9300 are not in use

### Keycloak Authentication Issues

1. Verify the realm name matches the configuration
2. Check the issuer URI is correct
3. Ensure the JWT is not expired
4. Verify client configuration in Keycloak

### Storage Initialization

If you encounter storage initialization errors, ensure:
1. The application has write permissions to the storage directory
2. The path specified in `app.storage.location` is valid

## Future Enhancements

- [ ] REST controller implementation
- [ ] Advanced search filters (price range, rating, cuisine type)
- [ ] Recommendation system
- [ ] Image optimization and thumbnail generation
- [ ] Caching layer with Redis
- [ ] API documentation with Swagger/OpenAPI
- [ ] Pagination and sorting improvements
- [ ] User favorites and bookmarks
- [ ] Real-time notifications

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the terms specified in the `pom.xml` file.

## Contact

For questions or support, please open an issue in the project repository.

---

**Note**: This is a development setup. For production deployment, additional security measures, monitoring, and infrastructure considerations are required.
