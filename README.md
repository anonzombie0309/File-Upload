
# 📁 File Storage Service

A Spring Boot application that allows users to:

- ✅ Upload files to AWS S3
- ✅ Save file metadata in MySQL
- ✅ Search files by name (within a user's folder)

---

## 🛠 Tech Stack

- Java 17
- Spring Boot 3.1+
- Spring Data JPA
- AWS SDK v1 (S3)
- MySQL
- Postman Collection (included)

---

## ⚙️ Configuration

Update `src/main/resources/application.properties`:

```properties
# AWS S3
cloud.aws.credentials.access-key=YOUR_ACCESS_KEY
cloud.aws.credentials.secret-key=YOUR_SECRET_KEY
cloud.aws.region.static=us-east-1
cloud.aws.s3.bucket-name=your-bucket-name

# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/filestorage_db
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## ▶️ Running the Application

```bash
./mvnw spring-boot:run
```

Or build & run:

```bash
mvn clean package
java -jar target/filestorage-1.0.0.jar
```

---

## 🔌 API Endpoints

### 1. `POST /upload`

Upload a file.

**Header**:
- `userName`: string

**Body**:
- `file`: Multipart file

---

### 2. `GET /search`

Search for files by name.

**Body**:
- `userName`: string
- `fileName`: string

**Response**:
```json
[
  {
    "fileName": "Backend Assignment.pdf",
    "s3Url": "https://your-bucket.s3.amazonaws.com/userName/Backend Assignment.pdf"
  }
]
```

---

## 📬 Postman Collection

Included file: `FileUpload Assignment.postman_collection.json`

Steps:
1. Open Postman
2. Import the file
3. Hit `/upload`, `/search` with params

---
