'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
    NODE_ENV: '"development"',
    AUTH0_REDIRECTURI: '"http://localhost:8080/auth0callback"'
})
