# Car Project

Täislahendus kasutaja kontaktandmete ja automargi valiku salvestamiseks. Rakendus koosneb:

- Vue 3 frontendist (Vite)
- Spring Boot backendist (Java 21, Gradle)
- PostgreSQL andmebaasist (Flyway migratsioonid)
- Docker Compose

## Kiire kasutusjuhend

- Tavakasutaja
- Avab avalehe
- Sisestab nime, telefoni, valib automargi ja mudeli
- Kui valik puudub, saab "Muu" kaudu sisestada soovitud nime
- Salvestamisel andmed talletatakse ja sessioon jäetakse meelde
- Kasutaja saab sama sessiooni jooksul andmeid muuta

- Administraator
- Admin liides: http://localhost:5173/admin
- Kuvab kõik esitatud soovid tabelis

## Käivitus
**NB**: 
- Lokaalselt run-ides peab application.yml failis olema 'jdbc:postgresql://localhost:5432/car_db' ja vite.config.js-is on target: 'http://localhost:8080'
- Dockeris vite.config.js failis target on: 'http://car-backend:8080' ja application.yml-is 'jdbc:postgresql://car-db:5432/car_db'

1. Kui lokaalselt soovitakse käivitada, siis peab olemas olema:
   - [Node.js](https://nodejs.org/) 
   - npm install
   - npm run dev
   - http://localhost:5173 ja päringute kuvamiseks - http://localhost:5173/admin
   - [Java 21](https://adoptium.net/) 
   - ./gradlew bootRun või BackendApplication - run
   - docker compose up --build
  
2. Kui Dockeriga soovitakse käivitada, siis peab olema:
   - [Docker](https://www.docker.com/)
ja siis:
  - bash
  - docker compose up --build
  - Frontend: http://localhost:5173 ja päringute kuvamiseks - http://localhost:5173/admin
  - Backend: http://localhost:8080/api/ping
  - pgAdmin: http://localhost:5050 (username ja pw on docker-compose.yml'is)

3. Kui vaja pgAdminnis käsitsi DB luua, siis: 
- Register Server -> name: car_db; 
- Connection -> host: car_db
              - port: 5432
              - maintance: postgres
              - username: [in docker-compose.yml]
              - password: [in docker-compose.yml]



