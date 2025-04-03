# Car Project

Täislahendus kasutaja kontaktandmete ja automargi valiku salvestamiseks. Rakendus koosneb:

- Vue 3 frontendist (Vite)
- Spring Boot backendist (Java 21, Gradle)
- PostgreSQL andmebaasist (Flyway migratsioonid)
- Docker Compose

## Käivitus
1. Kui lokaalselt soovitakse käivitada, siis peab olemas olema:
   - [Node.js](https://nodejs.org/) 
   - [Java 21](https://adoptium.net/) 
  
2. Kui Dockeriga soovitakse käivitada, siis peab olema:
   - [Docker](https://www.docker.com/)
ja siis:
  bash
  docker compose up --build
  Frontend: http://localhost:5173
  Backend: http://localhost:8080/api/ping
  pgAdmin: http://localhost:5050 (username ja pw on docker-compose.yml'is)

Kui vaja pgAdminnis käsitsi DB luua, siis: 
Register Server -> name: car_db; 
Connection -> host: car_db
              port: 5432
              maintance: postgres
              username: [in docker-compose.yml]
              password: [in docker-compose.yml]



