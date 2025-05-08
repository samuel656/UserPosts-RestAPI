# 📱 Social Media REST API

This is a Spring Boot-based REST API that simulates a basic social media platform. It supports user and post management with full CRUD operations and demonstrates key Spring Boot concepts such as Dispatcher Servlet, HTTP method mappings, and global exception handling.

---

## 🚀 Features

- CRUD operations for **Users** and **Posts**
- RESTful API request handling using Spring MVC annotations
- Centralized exception handling using `@ControllerAdvice`
- Use of `ResponseEntity` for customizable HTTP responses
- JSON request/response handling using Jackson
- Tested using Talend API Tester (Chrome Extension)

---

## 🔄 Request Handling (Spring MVC)

- **DispatcherServlet** acts as the front controller for all HTTP requests.
  - Automatically maps requests to controllers via `/`
  - Configured using `DispatcherServletAutoConfiguration`

---

## ⚠️ Error Handling

- **Default error pages** (White-label error) for unmatched URLs
- Powered by `ErrorMvcAutoConfiguration`
- Custom exception handling:
  - `@ExceptionHandler` – For controller-specific errors
  - `@ControllerAdvice` – For global error handling

---

## 📦 Dependencies (Spring Boot Starter)

- Spring Boot
- Spring MVC
- Embedded Tomcat
- Jackson (for JSON serialization)
