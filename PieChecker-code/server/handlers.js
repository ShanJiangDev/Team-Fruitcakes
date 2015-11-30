var fs = require('fs'),
    url = require('url'),
    path = require('path'),
    async = require('async'),
    https = require('https'),
    _ = require('underscore'),
    listeners = {},
    broadcasters = {};

process.handlers = {
  redirect: redirect,
  socketio: socketio,
  fileserver: fileserver
};


function redirect(req, res){

  res.writeHead(302, {'Location': 'https://piechecker.com' + req.url});

  res.end();

}


function socketio(io, socket) {

  var watching;

  console.log(socket.handshake.query);

  socket.on('message', function(data) {

    console.log('received', data);

    io.sockets.in(data.channel).emit('temperature', data);

  });

  socket.on('watch', function(data) {

    console.log('watch: ', data);

    if(watching) socket.leave(watching);

    socket.join(data);

    watching = data;

  });

  socket.on('login', function(data) {

    var req = https.request({
      host: 'api.twitch.tv',
      port: 443,
      path: '/kraken/channel',
      method: 'GET',
      headers: { 'Accept': 'application/vnd.twitchtv.v2+json', 'Authorization': 'OAuth ' + data.token }
    }, function(res) {
      var raw = '';
      res.on('data', function(d){ raw += d; });
      res.on('end', function(){

        var channel = JSON.parse(raw);

        var req = https.request({
              host: 'api.twitch.tv',
              port: 443,
              path: '/kraken/channels/' + channel.name + '/videos',
              method: 'GET',
              headers: { 'Accept': 'application/vnd.twitchtv.v2+json', 'Authorization': 'OAuth ' + data.token }
            }, function(res) {
              var data = '';
              res.on('data', function(d){ data += d; });
              res.on('end', function(){ socket.emit('login', { channel: channel, videos: JSON.parse(data) }); });
            });

            req.end();

      });

    });

    req.end();

  });

}


function fileserver(req, res) {

  if(req.url.indexOf('/player.swf?') === 0) return player(req, res);

  var pathname = url.parse(req.url).pathname,
      filename = path.join(__dirname, '../client' + pathname);

  fs.exists(filename, function(exists) {

    var type;

    if(!exists || fs.lstatSync(filename).isDirectory()) {
      filename = path.join(process.cwd(), '../client/index.html');
    }

    switch(path.extname(filename)) {
      case '.css': type = 'text/css'; break;
      case '.html': type = 'text/html'; break;
      case '.htm': type = 'text/html'; break;
      case '.png': type = 'image/png'; break;
      case '.jpg': type = 'image/jpeg'; break;
      case '.ico': type = 'image/x-icon'; break;
      case '.js': type = 'application/javascript'; break;
      default: type = 'application/octet-stream'; break;
    }

    fs.readFile(filename, 'binary', function(err, file) {
      if(err) {
        res.writeHead(500, {'Content-Type': 'text/plain'});
        res.write(err + '\n');
        res.end();
        return;
      }

      res.writeHead(200, {'Content-Type': type});
      res.write(file, 'binary');
      res.end();
    });
  });
}


function player(req, res) {

  var channel = url.parse(req.url).query.split('=')[1];

  var req = require('http').request({
    host: 'www.twitch.tv',
    port: 80,
    path: '/widgets/live_embed_player.swf?channel=' + channel,
    method: 'GET'
  }, function(r) {
    res.writeHead(r.statusCode, r.headers);
    r.pipe(res);
  });

  req.end();

  console.log(channel);
}
