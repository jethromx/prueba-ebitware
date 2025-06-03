
const express = require('express');
const fileUpload = require('express-fileupload');
const cors = require('cors');

const { dbConnection } = require('../database/config');

class Server {

    constructor() {
        this.app = express();
        this.port = process.env.PORT || 3000;

        this.conectarDB();
        this.middleware();
        this.routes();
    }

    async conectarDB() {
        await dbConnection();
    }
    
    // configuration routes
    routes() {
       this.app.use('/api/import-csv', require('../routes/csvroutes.js')); 
    }

    middleware() {

        this.app.use(cors())

        // Middleware to parse JSON bodies
        this.app.use(express.json());
        
        // Middleware to parse URL-encoded bodies
        this.app.use(express.urlencoded({ extended: true }));

        // Middleware to handle file uploads
        this.app.use(fileUpload({
            useTempFiles : true,
            tempFileDir : './uploads/',
        }));
        
        
    }
    
    start() {
        this.app.listen(this.port, () => {
        console.log(`Server is running on port ${this.port}`);
        });
    }

}

module.exports = Server;