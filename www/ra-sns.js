var exec = require('cordova/exec');

var SNSCordova = function() {};

SNSCordova.prototype.create = function(id) {
  exec(console.log, console.error, 'sns', 'create', [id]);
};

module.exports = SNSCordova;