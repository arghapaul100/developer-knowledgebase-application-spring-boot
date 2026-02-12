# Developer Knowledge Base Application

Production-ready monorepo for an internal StackOverflow-like platform where engineering teams capture solutions for recurring technical issues.

## Tech Stack

- **Frontend:** React + Vite + React Router + Axios
- **Backend:** Spring Boot 3, Java 17, Spring Security, JPA, JWT, Swagger
- **Database:** PostgreSQL (with full-text search support)
- **Infra:** Docker, Docker Compose

## Architecture

- `frontend/` single-page app for authentication, entry creation/editing, search, and viewing entries.
- `backend/` layered Spring Boot API with modules:
  - `controller`
  - `service`
  - `repository`
  - `dto`
  - `entity`
  - `security`
  - `exception`
  - `config`
- `db/init.sql` initializes schema and indexes.

## API Endpoints

### Auth
- `POST /api/auth/register`
- `POST /api/auth/login`

### Knowledge Entries
- `POST /api/knowledge`
- `GET /api/knowledge`
- `GET /api/knowledge/{id}`
- `PUT /api/knowledge/{id}`
- `DELETE /api/knowledge/{id}`

### Search
- `GET /api/knowledge/search?query=&category=&tag=`

## Run Locally (without Docker)

1. Copy environment file:
   ```bash
   cp .env.example .env
   ```
2. Start PostgreSQL manually.
3. Backend:
   ```bash
   cd backend
   mvn spring-boot:run
   ```
4. Frontend:
   ```bash
   cd frontend
   npm install
   npm run dev
   ```

## Run with Docker Compose

```bash
cp .env.example .env
docker compose up --build
```

Services:
- Frontend: `http://localhost:5173`
- Backend: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

## Security Notes

- JWT-based authentication
- BCrypt password hashing
- Public read access for entries, authenticated access required for write operations

## Screenshots

- Home page placeholder: `docs/screenshots/home.png`
- Search page placeholder: `docs/screenshots/search.png`

## Future Improvements

- Exception fingerprint auto-suggestion
- Voting and popularity ranking
- Admin moderation panel
