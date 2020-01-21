const express = require('express');
const router = express.Router();
const serverController = require('../controllers/servers');

router.get('/', serverController.getAll);
router.get('/:id', serverController.getById);

module.exports = router;