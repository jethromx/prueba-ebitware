
const  { Router } = require('express');
const multer = require('multer');
const { processCSV } = require('../controller/cvsController.js');

const router = Router();
//const upload = multer({ dest: 'uploads/' }); // Carpeta temporal

router.post('/',processCSV);


module.exports = router;