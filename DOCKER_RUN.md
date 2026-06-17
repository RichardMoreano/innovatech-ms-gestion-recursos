# Guía de Ejecución con Docker - Microservicio de Gestión de Recursos V2

Este componente maneja el inventario de capital humano de la organización bajo arquitectura clásica por capas (CSR).

## 1. Prerrequisitos e Infraestructura
* **Red del Ecosistema:** `innovatech-net`.
* **Motor Relacional:** Enlazado al contenedor base `innovatech-db`.

## 2. Puertos
* **Interno/Externo:** `8082` (Expuesto perimetralmente para consumo del BFF a través del API Gateway).

## 3. Despliegue
```bash
docker compose up -d --build ms-gestion-recursos