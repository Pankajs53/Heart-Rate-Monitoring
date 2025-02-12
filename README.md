# Heart Rate Monitoring - Assignment Submission

## Overview
This project involves the implementation of a Heart Rate Monitoring system. We have created three models to manage data effectively, ensuring structured relationships between entities. The project utilizes `One-to-One` and `One-to-Many` mappings to maintain data integrity and enable efficient retrieval.

## Models and Their Purpose

### 1. **Users Model**
   - Represents the users of the system (e.g., a patient or an admin).
   - Attributes: `id`, `email`, `password` (hashed), etc.
   - **One-to-One relationship** with `Patients` (each user has exactly one associated patient record).

### 2. **Patients Model**
   - Stores personal details of a patient.
   - Attributes: `id`, `name`, `age`, `gender`, `phoneNo`, etc.
   - **One-to-One relationship** with `Users` (each patient is linked to exactly one user account).
   - **One-to-Many relationship** with `HeartRate` (one patient can have multiple heart rate records over time).

### 3. **HeartRate Model**
   - Stores heart rate measurements.
   - Attributes: `id`, `bpm`, `timeStamp`, `patient_id`.
   - **Many-to-One relationship** with `Patients` (each heart rate entry belongs to a single patient).

## Assumptions Made
- Each `User` is uniquely associated with a `Patient`.
- A `Patient` can have multiple heart rate records.
- The system captures and stores heart rate (`bpm`) along with the timestamp when recorded.
- The password field in the `Users` model is hashed and should not be exposed in responses.
- Timestamps should not be `NULL` when storing heart rate data.
- When logged in, we can simply store the `user_id` in localStorage instead of asking for `user_id` while saving patient data. However, since we are not using localStorage, cookies, or JWT tokens currently, we are sending `user_id` externally with the API request.
- Data validation can be improved significantly, and additional response handling could be implemented if more time were available.

## API Endpoints

### 1. **User Registration**
   - **Endpoint:** `POST /users/register`
   - **Request Body (JSON):**
     ```json
     {
       "email": "user@example.com",
       "password": "securePassword"
     }
     ```
   - **Response:**
     ```json
     {
       "message": "User registered successfully"
     }
     ```

### 2. **User Login**
   - **Endpoint:** `POST /users/login`
   - **Request Body (JSON):**
     ```json
     {
       "email": "user@example.com",
       "password": "securePassword"
     }
     ```
   - **Response:**
     ```json
     {
       "token": "jwt-token-here"
     }
     ```

### 3. **Add Patient**
   - **Endpoint:** `POST /patients/add`
   - **Request Body (JSON):**
     ```json
     {
       "userId": "2",
       "name": "John Doe",
       "age": 30,
       "gender": "Male",
       "phoneNo": "9876543210"
     }
     ```
   - **Response:**
     ```json
     {
       "message": "Patient record added successfully"
     }
     ```

### 4. **Retrieve Patient Details**
   - **Endpoint:** `GET /patients/{patientId}`
   - **Response:**
     ```json
     {
       "id": 1,
       "name": "John Doe",
       "age": 30,
       "gender": "Male",
       "phoneNo": "9876543210"
     }
     ```

### 5. **Add Heart Rate Record**
   - **Endpoint:** `POST /heart-rate/add`
   - **Request Body (JSON):**
     ```json
     {
       "patientId": 1,
       "bpm": 85
     }
     ```
   - **Response:**
     ```json
     {
       "message": "Heart rate record added successfully"
     }
     ```

### 6. **Get Heart Rates for a Patient**
   - **Endpoint:** `GET /heart-rate/patients/{patientId}`
   - **Response:**
     ```json
     [
       {
         "bpm": 80,
         "timeStamp": "2025-02-12T11:28:23.375306"
       },
       {
         "bpm": 90,
         "timeStamp": "2025-02-12T11:30:10.123456"
       }
     ]
     ```


## Testing Instructions
- Use Postman or any API testing tool to send requests.
- Ensure the database connection is properly set up before testing.
- Validate responses to check if the correct data is retrieved or stored.

