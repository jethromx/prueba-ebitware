const { Schema, model } = require('mongoose');

const userSchema = new Schema({
    username:{
        type: String,
        required: [true, 'Username is required'],
    },

    identifier:{
        type: String,
        required: [true, 'Identifier is required'],
    },
    firstname:{
        type: String,
        required: [true, 'Firstname is required']
    }, 
    lastname:{
        type: String,
        required: [true, 'Lastname is required']
    }});

    userSchema.methods.toJSON = function() {
        const { __v, _id, ...user } = this.toObject();
        user.uid = _id;
        return user;
    }

    module.exports = model('User', userSchema);