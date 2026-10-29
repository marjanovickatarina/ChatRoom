# ChatRoom

A simple client-server chat application written in Java.

## Overview

ChatRoom is a TCP-based chat application that demonstrates client-server communication in Java. The project separates client-side and server-side functionality into dedicated classes.

## Technologies

- Java
- TCP sockets
- Multithreading

## Project Structure

```text
ChatRoom/
├── src/
│   ├── ChatClient.java
│   ├── ChatServer.java
│   ├── ReadMsg.java
│   ├── WriteMsg.java
│   └── Worker.java
└── README.md
```

## How to Run

1. Open the project in IntelliJ IDEA.
2. Compile the Java source files.
3. Start `ChatServer`.
4. Start one or more instances of `ChatClient`.
5. Connect the clients to the server using the configured host and port.

## Purpose

This project was developed to practise Java networking, TCP socket communication, and concurrent client handling.