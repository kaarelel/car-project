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


## Struktuur

car-project/
├── .idea/                          #Siin on .idea läbi tulnud - vale
├── backend/
│   ├── Dockerfile
│   ├── build.gradle
│   ├── settings.gradle
│   └── src/
│       ├── main/
│       │   ├── java/com/carproject/backend/
│       │   │   ├── BackendApplication.java
│       │   │   ├── config/
│       │   │   ├── controller/
│       │   │   ├── domain/enums/
│       │   │   ├── error/
│       │   │   ├── model/
│       │   │   ├── repo/
│       │   │   ├── request/
│       │   │   ├── response/
│       │   │   └── service/
│       │   └── resources/
│       │       ├── application.yml
│       │       └── db/migration/
│       │           ├── V1__init_schema.sql
│       │           ├── V2__insert_first_cars.sql
│       │           ├── V3__fill_car_brand_names.sql
│       │           ├── V4__insert_initial_modals.sql
│       │           └── V5__insert_car_brand_name.sql
│       └── test/
│           └── java/...
├── frontend/
│   ├── Dockerfile
│   ├── package.json
│   ├── vite.config.js
│   ├── public/
│   └── src/
│       ├── App.vue
│       ├── main.js
│       ├── components/
│       ├── views/
│       └── assets/
├── docker-compose.yml
├── README.md
├── .gitignore
├── gradlew
├── gradlew.bat

