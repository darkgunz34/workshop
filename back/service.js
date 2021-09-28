const speakeasy = require("speakeasy");
const res = require("express");
const uuid = require("uuid")

const { Pool, Client } = require('pg');

const pool = new Pool({
    user: 'stephanparichon_workshop',
    host: 'postgresql-stephanparichon.alwaysdata.net',
    database: 'stephanparichon_workshop',
    password: 'Epsi#1234!',
    port: 5432,
})
/*pool.query('SELECT NOW()', (err, res) => {
    console.log(err, res)
    pool.end()
})*/
const client = new Client({
    user: 'stephanparichon_workshop',
    host: 'postgresql-stephanparichon.alwaysdata.net',
    database: 'stephanparichon_workshop',
    password: 'Epsi#1234!',
    port: 5432,
})
client.connect()
/*client.query('SELECT NOW()', (err, res) => {
    console.log(err, res)
})
client.query('SELECT * FROM ref_user',(err,res)=>{
    console.log(err, res)
})
var CronJob = require('cron').CronJob;
var job = new CronJob('*!/2 * * * *', function() {
    console.log('You will see this message every second');
}, null, true, 'America/Los_Angeles');
job.start();*/


const getUserById = (request, response) => {
    const id = parseInt(request.params.id)
    pool.query('SELECT * FROM ref_user WHERE id = $1', [id], (error, results) => {
        if (error) {
            response.status(401);
            throw error
        }
        response.status(200).json(results.rows)
    })
}
const createUser = (request, response) => {
    const { user_name, password } = request.body
    console.log(user_name, password);
    pool.query('INSERT INTO ref_user (user_name, password) VALUES ($1, $2)', [user_name, password], (error, results) => {
        if (error) {
            response.status(401);
            throw error
        }
        response.status(201).send(`User added with ID: ${results.insertId}`)
    })
}
const getTokenById = (request, response) => {
    const id = parseInt(request.params.id)
    pool.query('SELECT * FROM token WHERE id = $1', [id], (error, results) => {
        if (error) {
            response.status(401);
            throw error
        }
        response.status(200).json(results.rows)
    })
}
const createToken = (request, response) => {
    //app.use(cookieParser());
    let secret32 = uuid.v4();
    console.log(secret32)
    pool.query('INSERT INTO token (token) VALUES ($1)', [secret32], (error, results) => {
        if (error) {
            response.status(401);
            throw error

        }
        response.status(201).send(secret32);
        /*response.status(201).send(`User added with ID: ${results.insertId}`)*/
    })
}
const updateToken = (request, response) => {
    const id = parseInt(request.params.id)
    console.log(request);
    var tokenGen = uuid.v4();
    pool.query(
        'UPDATE token SET token = $1 WHERE id = $2',
        [tokenGen , id],
        (error, results) => {
            if (error) {
                response.status(401);
                throw error
            }
            response.status(201).send('User added - Cookie:' + response.cookie('pyucook',tokenGen, { maxAge: 900000, httpOnly: true }));
        }
    )
}


const checkToken = (request, response) => {
    const token = request.params.token
    console.log(request)
    console.log('coucou toto'+token)

    pool.query(
        'UPDATE token SET token_datetime = CURRENT_TIMESTAMP WHERE token = $1',
        [token],
        (error, results) => {
            if (error) {
                response.status(401);
                throw error
            }
            response.status(201).send('Date update');
        }
    )
}
// La fonction pour la vérification des users
/*const checkUser = (request, response) => {


    pool.query(
        'UPDATE token SET token_datetime = NOW() WHERE token = $1',
        [token],
        (error, results) => {
            if (error) {
                response.status(401);
                throw error
            }
            response.status(201).send('Date update');
        }
    )
}*/

module.exports = {
    getUserById,
    createUser,
    getTokenById,
    createToken,
    updateToken,
    checkToken,
    checkUser

}
