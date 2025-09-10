// db.js
const { Sequelize } = require('sequelize');

// aqui se define o banco a ser acessado
const sequelize = new Sequelize({
  dialect: 'sqlite',
  storage: 'banco.sqlite' // ou use um banco real, como PostgreSQL/MySQL
});

module.exports = sequelize;