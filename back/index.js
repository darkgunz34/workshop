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
app.post('/users/create/:name&:password&:email', db.createUser);
app.post('/token', db.createToken);
app.put('/token/:id', db.updateToken);
app.post('/users/:token', db.checkToken);
app.put('/users/:user&:password', db.createUser);
app.post('/users/verify/:name&:password', db.tryConnectUser);
app.post('/users/deco/:token', db.SuppTokenFromDb);
app.listen(port, () => {
    console.log(`App running on port ${port}.`)
})
