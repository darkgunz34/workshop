const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const db = require('./service.js');
const port = 3000
app.use(bodyParser.json())
app.use(
    bodyParser.urlencoded({
        extended: true,
    })
)

app.get('/', (request, response) => {
    response.json({ info: 'Node.js, Express, and Postgres API' })
})

/*server.post('/login', function(req, res){
    let login = req.body.login;
    let password = req.body.password;
    app.use(cookieParser());
    let token = service.getToken();
    res.status(201).send('cookie:' + res.cookie('pyucook',token, { maxAge: 900000, httpOnly: true }));
});*/

app.get('/users/:id', db.getUserById);
app.post('/users', db.createUser);
app.post('/token', db.createToken);


app.listen(port, () => {
    console.log(`App running on port ${port}.`)
})
