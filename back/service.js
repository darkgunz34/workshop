const speakeasy = require("speakeasy");
const res = require("express");
const uuid = require("uuid")
const bcrypt = require('bcryptjs');

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
    const { user_name, password, email } = request.body
    let saltRounds = 10;
    bcrypt.hash(password, saltRounds, function(err, hash) {
        pool.query(
            'INSERT INTO ref_user(user_name, password, email) VALUES($1, $2, $3)',
            [user_name, hash, email],
            (error, results) => {
                if (error) {
                    response.status(401);
                    throw error
                } else {
                    response.status(201).send(`User added with ID: ${results.insertId}`)
                }
            }
        )
    });
}
const getTokenById = (request, response) => {
    const id = parseInt(request.params.id)
    pool.query('SELECT * FROM token WHERE id = $1', [id], (error, results) => {
        if (error) {
            response.status(401);
            throw error
        } else {
            response.status(200).json(results.rows)
        }
    })
}
const createToken = (response) => {
    //app.use(cookieParser());
    let secret32 = uuid.v4();
    pool.query('INSERT INTO token (token) VALUES ($1)', [secret32], (error, results) => {
        if (error) {
            response.status(401);
            throw error
        } else {
            //response.status(201).send('cookie' + response.cookie('pyucook',secret32, { maxAge: 900000, httpOnly: true }));
            response.status(201).json(secret32);

        }
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
            else {
                response.status(201).send('User added - Cookie:' + response.cookie('pyucook',tokenGen, { maxAge: 900000, httpOnly: true }));
            }
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
            } else {
                response.status(201).send('Date update');
            }
        }
    )
}
// La fonction pour la vérification des users
const tryConnectUser = (request, response) => {
    let pass = request.params.password;
    let name = request.params.name;
        pool.query(
            'SELECT password FROM ref_user WHERE user_name = $1',
            [name],
            (error, results) => {
                if (error) {
                    response.status(401);
                    throw error
                }
                bcrypt.compare(pass, results.rows[0].password, function(err2, result2) {
                    if(result2) {
                        createToken(response);
                    } else {
                        response.status(401).send('rentre chez toi');
                    }
                });

            }
        )
}
module.exports = {
    getUserById,
    createUser,
    getTokenById,
    createToken,
    updateToken,
    checkToken,
    tryConnectUser

}
