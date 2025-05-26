# API REST CSV MongoDB

API REST para procesar archivos CSV y almacenarlos en MongoDB.

## Tecnologías 

- Node.js
- Express
- Express-fileupload 
- MongoDB
- CSV Parser
- Dotenv

## Requisitos Previos 

- Node.js 18+
- MongoDB
- Docker (opcional)

## Variables de Entorno 

```
MONGODB_CNN=mongodb://mongoadmin:mongopass@localhost:27017/ebitware_mongo?authSource=admin
PORT=3000
```

## Instalación 

1. **Clonar el repositorio**
```bash
git clone <url-repositorio>
cd cvs-node
```

2. **Instalar dependencias**
```bash
npm install
```

3. **Iniciar en desarrollo**
```bash
npm run dev
```

## Base de Datos con Docker (necesario para base de datos mongo local)

```bash
docker compose up -d
```

## Endpoints 🌐

### Importar CSV
```http
POST {{baseUrl}}:{{port}}/api/import-csv
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW

------WebKitFormBoundary7MA4YWxkTrZu0gW
Content-Disposition: form-data; name="file"; filename="username.csv"
Content-Type: text/csv

< ./username.csv
------WebKitFormBoundary7MA4YWxkTrZu0gW--
```

## Estructura del Proyecto 

```
cvs-node/
├── models/
│   ├── server.js     # Configuración del servidor
│   └── user.js       # Modelo de usuario
├── routes/
│   └── csvroutes.js  # Rutas para CSV
├── controller/
│   └── cvsController.js # Lógica de negocio
├── database/
│   └── config.js     # Configuración de conexion a MongoDB
└── app.js            # Punto de entrada
```

## Formato CSV 

```csv
Username;Identifier;Firstname;Lastname
booker12;9012;Rachel;Booker
grey07;2070;Laura;Grey
```

## Scripts Disponibles 

```json
{
  "dev": "nodemon app.js",
  "start": "node app.js"
}
```

## Características 

- Procesamiento de archivos CSV
- Validación de formato
- Almacenamiento en MongoDB
- Ejemplos de peticiones HTTP