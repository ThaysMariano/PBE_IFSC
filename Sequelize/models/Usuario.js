// models/Usuario.js
const { DataTypes } = require('sequelize');
const sequelize = require('../db');

// a definição de uma entidade (ou modelo)
// a função "define" tem este formato: define(nome_da_entidade, atributos, opções)
// atributos: as colunas da tabela
// opções: tipicamente o nome da tabela no banco
const Usuario = sequelize.define('Usuario', {
  nome: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  sobrenome: {
    type: DataTypes.STRING,
    allowNull: false,
  }
}, {
  tableName: 'Usuarios',
  timestamps: false,
});

module.exports = Usuario;