
const { Pool, Client } = require('pg');

const pool = new Pool({
    user: 'stephanparichon_workshop',
    host: 'postgresql-stephanparichon.alwaysdata.net',
    database: 'stephanparichon_workshop',
    password: 'Epsi#1234!',
    port: 5432,
})
pool.query('SELECT NOW()', (err, res) => {
    console.log(err, res)
    pool.end()
})
const client = new Client({
    user: 'stephanparichon_workshop',
    host: 'postgresql-stephanparichon.alwaysdata.net',
    database: 'stephanparichon_workshop',
    password: 'Epsi#1234!',
    port: 5432,
})
client.connect()
client.query('SELECT NOW()', (err, res) => {
    console.log(err, res)
})
client.query('SELECT * FROM ref_user',(err,res)=>{
    console.log(err, res)
})
var CronJob = require('cron').CronJob;
var job = new CronJob('*/2 * * * *', function() {
    console.log('You will see this message every second');
}, null, true, 'America/Los_Angeles');
job.start();


const getUserById = (request, response) => {
    const id = parseInt(request.params.id)
    pool.query('SELECT * FROM ref_user WHERE id = $1', [id], (error, results) => {
        if (error) {
            throw error
        }
        response.status(200).json(results.rows)
    })
}
const createUser = (request, response) => {
    const { name, email } = request.body
    pool.query('INSERT INTO ref_user (name, email) VALUES ($1, $2)', [name, email], (error, results) => {
        if (error) {
            throw error
        }
        response.status(201).send(`User added with ID: ${results.insertId}`)
    })
}
const getTokenById = (request, response) => {
    const id = parseInt(request.params.id)
    pool.query('SELECT * FROM token WHERE id = $1', [id], (error, results) => {
        if (error) {
            throw error
        }
        response.status(200).json(results.rows)
    })
}
const createToken = (request, response) => {
    const { name, email } = request.body
    pool.query('INSERT INTO token (name, email) VALUES ($1, $2)', [name, email], (error, results) => {
        if (error) {
            throw error
        }
        response.status(201).send(`User added with ID: ${results.insertId}`)
    })
}
module.exports = {
    getUserById,
    createUser,
    getTokenById,
    createToken
}