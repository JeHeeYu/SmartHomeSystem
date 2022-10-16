const http = require('http');
const express = require('express');
//const db = require('../models');

app = express();

const port = 3000;

app.set('port', port);


app.listen(app.get('port'), () => {
    console.log(`Start Server on Port : ${port}`);
});