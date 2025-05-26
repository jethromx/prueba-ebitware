
require('dotenv').config();

const Server = require('./models/server.js');


const server = new Server();

// Start the server
server.start();

