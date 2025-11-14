# 🟣 DailyPulse – Habit & Mood Tracker
A clean, full-stack Spring Boot application that helps users track daily habits, log mood levels, and view analytics across any date range. DailyPulse demonstrates real-world backend architecture, REST API design, analytics computation, and a lightweight frontend — perfect for learning Spring Boot and showcasing practical software engineering skills.

## 🚀 Features
### Habit Management
- Add, view, update, and delete habits
- Automatically records createdAt timestamp
- Prevents duplicate habit names

### Daily Check-ins
- Log date, mood (1–5), notes, and completed habits
- Input validation for cleaner data
- Data stored using JPA + H2 in-memory database

### Analytics Summary
Endpoint: /api/analytics/summary?from=YYYY-MM-DD&to=YYYY-MM-DD  
Returns:
- Total check-ins
- Average mood
- Total completed habits

### Global Error Handling
The API returns clean and consistent JSON responses for all errors using a global exception handler.

## 🧩 Architecture
Frontend (HTML/CSS/JS)
↓
REST Controllers
↓
Service Layer (Business Logic)
↓
Repository Layer (JPA)
↓
H2 In-Memory Database

## 🛠️ Tech Stack
Backend: Java 17+, Spring Boot 3, Spring Web, Spring Data JPA, H2, Maven  
Frontend: HTML5, CSS3, Vanilla JavaScript (Fetch API)

## 📂 Project Structure
src/main/java/com/dailypulse  
 ┣ controller  
 ┣ service  
 ┣ repository  
 ┣ model  
 ┣ dto  
 ┣ exception  
src/main/resources/static  
 ┣ index.html  
 ┣ styles.css  
 ┗ main.js  

## 📦 Running the Project
1. Start backend:
   mvnw.cmd spring-boot:run

2. Open UI:
   http://localhost:8080/

3. Access H2 Console:
   http://localhost:8080/h2-console  
   JDBC URL: jdbc:h2:mem:dailypulsedb

## 🧪 Example API Usage
Create Habit:
POST /api/habits  
Body:
{ "name": "Gym", "description": "45 mins workout" }

Log Check-in:
POST /api/checkins  
Body:
{ "date": "2025-11-12", "mood": 4, "notes": "Great day", "completedHabitIds": [1,2] }

Analytics Summary:
GET /api/analytics/summary?from=2025-11-01&to=2025-11-30

## 🎨 Frontend Summary
The frontend provides:
- Form to add habits
- Daily check-in UI with mood selector & checkboxes
- Auto-refresh 7-day analytics summary
All files served directly from Spring Boot’s static folder.

## 💡 Why This Project Matters
DailyPulse is structured like a real production backend:
- Clean layered architecture
- RESTful API design
- Analytics using Java Streams
- DTOs and global exception handling
- Full-stack integration with a polished UI
Perfect for showcasing Spring Boot skills on GitHub, resume, and LinkedIn.

## 🏁 Future Enhancements
- JWT Authentication
- Habit streak tracking
- Charts (Charts.js)
- PostgreSQL/MySQL profiles
- Scheduled reminders

