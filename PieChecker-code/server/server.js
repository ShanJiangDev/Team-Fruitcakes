var io,
    server,
    fs = require('fs'),
    http = require('http'),
    https = require('https'),
    socketIO = require('socket.io');

require('./handlers');
require('./head');

server = https.createServer({
  pfx: fs.readFileSync(__dirname + '/ssl/piechecker.pfx'),
  passphrase: 'ikgaeropuit'
}, process.handlers.fileserver);

io = socketIO.listen(server, { log: false })

io.sockets.on('connection', function(s){ process.handlers.socketio(io, s); });

server.listen(4430, '0.0.0.0');

http.createServer(process.handlers.redirect).listen(8000, '0.0.0.0');
