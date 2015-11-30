var io = require('socket.io-client'),
    socket = io.connect('https://piechecker.com', { port: 443 });

socket.on('connect', function () {

  console.log("socket connected");

  var sensor = require('child_process').spawn(
    'python',
    ['temperature.py'],
    { stdio: ['pipe', 'pipe', 'pipe'] }
  ).stdout;

  sensor.on('data', function(data){
    console.log(String(data));
    socket.emit('message', { channel: 'luuktv', value: String(data) });
  });
});
