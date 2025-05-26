const { response } = require('express');

const fs = require('fs');
const csv = require('csv-parser');
const User = require('../models/user');


const processCSV = async(req,res = response ) => {
    
    if (!req.files || Object.keys(req.files).length === 0) {
        await fs.promises.unlink(req.files.file.tempFilePath);
        console.log('Archivo temporal eliminado:', req.files.file.tempFilePath);
        return res.status(400).json('No files were uploaded.');
    }

    if (!req.files.file.mimetype.includes('csv') || !req.files.file.name.includes('csv') ) {
        await fs.promises.unlink(req.files.file.tempFilePath);
        console.log('Archivo temporal eliminado:', req.files.file.tempFilePath);
        return res.status(400).json('Invalid file type. Please upload a CSV file.');
    }


    console.log('File recived :', req.files.file);
    const csvFilePath = req.files.file.tempFilePath;
    
    try {
        // Leer el archivo CSV
        const results = await readCSVFile(csvFilePath);
        
        // Procesar y guardar usuarios
        const savedUsers = await Promise.all(
            results.map(item => saveUser(item))
        );

        // Eliminar el archivo temporal
        await fs.promises.unlink(csvFilePath);
        console.log('Archivo temporal eliminado:', csvFilePath);

        res.json({
            msg: 'Archivo CSV procesado correctamente',
            usuarios_procesados: savedUsers.length,
            usuarios: savedUsers
        });

    } catch (error) {
        console.error('Error:', error);
        res.status(500).json({ error: 'Error al procesar el archivo CSV' });
    }
};


// Función para leer el archivo CSV
const readCSVFile = (filePath) => {
    return new Promise((resolve, reject) => {
        const results = [];
        fs.createReadStream(filePath)
            .pipe(csv({
                separator: ';',
                skipLines: 1,
                headers: false
            }))
            .on('data', (data) => results.push(data))
            .on('end', () => resolve(results))
            .on('error', (error) => reject(error));
    });
};

// Función para guardar un usuario
const saveUser = async (item) => {
    try {
        const userData = {
            username: item['0'],
            identifier: item['1'],
            firstname: item['2'],
            lastname: item['3']
        };

        const user = new User(userData);
        return await user.save();
    } catch (error) {
        console.error('Error al guardar usuario:', error);
        throw error;
    }
};

module.exports = {
    processCSV
};