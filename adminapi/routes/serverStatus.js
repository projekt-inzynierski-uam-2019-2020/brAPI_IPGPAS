const express = require('express');
const router = express.Router();
const serverStatusController = require('../controllers/serverStatus');

router.get('/', serverStatusController.getServerStatus)

module.exports = router;