// server.js
const express = require('express');
const sequelize = require('./db');
const Usuario = require('./models/Usuario');
const port = 8080;

const app = express();
app.use(express.json());

sequelize.sync().then(() => {
  console.log("Banco de dados sincronizado.");
});

// Criar um novo usuário
app.post('/usuarios', async (req, res) => {
  const { nome, sobrenome } = req.body;
  const usuario = await Usuario.create({ nome, sobrenome });
  res.json({ id: usuario.id });
});

//busca por nome
app.get('/usuarios/nome/:nome', async (req, res) => {
  const usuario = await Usuario.findOne({ where: { nome: req.params.nome } });
  if (usuario) res.json(usuario);
  else res.status(404).send('Usuário não encontrado');
});

//busca por nomes iguais
app.get('/usuarios', async (req, res) => {
  const { nome } = req.query;
  const usuarios = await Usuario.findAll({ where: { nome } });
  res.json(usuarios);
});

//determinada string
const { Op } = require('sequelize');

app.get('/usuarios/comeca-com/:inicio', async (req, res) => {
  const usuarios = await Usuario.findAll({
    where: {
      nome: {
        [Op.startsWith]: req.params.inicio
      }
    }
  });
  res.json(usuarios);
});



app.listen(port, () => {
  console.log(`Servidor rodando em http://localhost:${port}`);
});