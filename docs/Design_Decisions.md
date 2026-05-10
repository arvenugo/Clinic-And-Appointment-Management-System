# DesignDecisions.md

## Clinic & Appointment Management System – Design Decisions

This document explains the key design principles and patterns used in the MediTrack project.

---

## 1. SOLID Principles

### 1.1 Single Responsibility Principle (SRP)

Each class has a single responsibility:

* `Appointment`,`Patient`, `Doctor` → hold entity data
* `AppointmentService`,`DoctorService`,`PatientService` → handles appointment logic
* `Validator` → centralised validation
* `CSVUtil` → handles file operations

**Benefit:** Improves maintainability and readability.

---

### 1.2 Open/Closed Principle (OCP)

Classes are open for extension but closed for modification.

Example:

* Billing strategies can be extended without modifying existing code
* New search filters can be added without changing core logic

**Benefit:** Safer feature additions and scalability.

---

### 1.3 Liskov Substitution Principle (LSP)

Subclasses can replace parent classes without breaking behavior.

Example:

* `Doctor` and `Patient` extend `Person`
* Any method expecting `Person` works correctly with both

**Benefit:** Ensures reliable inheritance.

---

### 1.4 Interface Segregation Principle (ISP)

Clients should not depend on unused methods.

Example:

* `Payable` → only billing-related methods
* `Searchable` → only search-related methods

**Benefit:** Keeps interfaces clean and focused.

---

### 1.5 Dependency Inversion Principle (DIP)

Depend on abstractions, not concrete implementations.

Example:

* Services depend on interfaces instead of concrete classes
* Strategy pattern for billing uses abstraction

**Benefit:** Reduces coupling and improves flexibility.

---

## 2. Singleton Pattern

### Usage:

* `IdGenerator` 

### Purpose:

Ensures only one instance exists across the application.

### Implementation (Lazy Initialization Example):


### Benefits:

* Controlled access to shared resources
* Avoids duplicate ID generation
* Saves memory

---

## 3. Strategy Pattern

### Usage:

* Billing system (different billing calculations)

### Approach:

Define a common interface for billing strategies and implement multiple variations.


### Benefits:

* Easily switch billing logic at runtime
* Extensible without modifying existing code
* Promotes OCP and DIP

---

## 4. Observer Pattern (Optional Feature)

### Usage:

* Appointment notifications

### Concept:

When an appointment status changes, notify multiple stakeholders.

### Components:

* **Subject:** Appointment
* **Observers:** PatientNotification system


### Flow:

1. Observer registers with subject
2. Appointment status changes
3. All observers are notified

### Benefits:

* Loose coupling between components
* Real-time updates
* Scalable notification system

---
## 5.Factory Design Pattern (Bill Generation)
### Usage:
Creating different types of Bill objects
### Problem:

Direct instantiation of different bill types using new leads to tight coupling and scattered logic.

### Solution:

Use a Factory class to centralize object creation.

## 6. Additional Design Choices

### 6.1 Immutable Class

* `BillSummary` is immutable
* Ensures thread safety and data integrity

---

### 6.2 Use of Enums

* `AppointmentStatus` (CONFIRMED, CANCELLED, PENDING)
* `Specialization`

**Benefit:** Avoids string-based errors and improves readability.

---


### 6.3 Exception Handling

* Custom exceptions (`InvalidDataException`, `AppointmentNotFoundException`)

**Benefit:** Clear error handling and debugging.

---

## 7. Conclusion

The MediTrack system is designed with a strong focus on:

* Clean architecture
* Low coupling and high cohesion
* Extensibility using design patterns
* Maintainability using SOLID principles

These design decisions ensure the system is scalable, testable, and easy to enhance in the future.

---
