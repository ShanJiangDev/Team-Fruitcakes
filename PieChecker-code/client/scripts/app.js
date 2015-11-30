var socket = io.connect('https://piechecker.com');

Twitch.init({clientId: 'ijan56ro6czlkrsoqb6dg2y2p46f1ej'}, function(error, status) {

  window.addEventListener("hashchange", navigate, false);

  socket.on('login', receiveLogin);

  socket.on('connect', function () {});
  socket.on('connecting', function () { });
  socket.on('disconnect', function () { });
  socket.on('connect_failed', function() { });
  socket.on('error', function () { });

  navigate();
  temperature();

  var p = location.pathname.substring(1);

  if(p) {
    reset();
    stream(p);
    return;
  }

  if(status.authenticated) return socket.emit('login', status);

  $('header').css('display', 'block');

  setup();

  function navigate(){

    var events = ['#sign-in', '#sign-out', '#set-up', '#the-team'];

    switch(events.indexOf(location.hash)) {
      case 0: login(); break;
      case 1: logout(); break;
      case 2: if(!status.authenticated) { setup(); } break;
      case 3: if(!status.authenticated) { team(); } break;
    }

    if(events.indexOf(location.hash) !== -1) location.hash = '';

  }

  function setup() {

    reset();
    
    document.body.className += ' frontPage';

    $('#installation').css('display', 'block');

    $('header').animate({ scrollLeft: 0 }, 300);

  }

  function team() {

    var $header = $('header');

    reset();

    document.body.className += ' frontPage';

    $('#team').css('display', 'block');

    $header.animate({ scrollLeft: $header.width() }, 300);

  }

  function receiveLogin(data) {

    console.log(data);

    document.body.className = 'signed-in';

    location.hash = '';

    stream(data.channel);

  }

  function stream(channel) {

    socket.emit('watch', channel.name);

    console.log('watching ', channel.name);

    document.body.className += ' stream';

    var s = $('#sign-in');

    s.attr('href', '/' + s.attr('href'));

    $('#player').html('<object type="application/x-shockwave-flash" height="100%" width="100%" id="live_embed_player_flash" data="https://piechecker.com/player.swf?channel=' + channel.name + '" bgcolor="#000000"><param name="allowFullScreen" value="true" /><param name="allowScriptAccess" value="always" /><param name="allowNetworking" value="all" /><param name="movie" value="https://piechecker.com/player.swf" /><param name="flashvars" value="hostname=www.twitch.tv&channel=' + channel.name + '&auto_play=true&start_volume=25" /></object>');

  }

  function login() {

    location.hash = '';

    if(!status || !status.token) return Twitch.login({
      redirect_uri: 'https://piechecker.com',
      scope: ['user_read', 'channel_read']
    });

    socket.emit('login', status);

  }

  function logout() {

    $('header').css('display', 'block');

    Twitch.logout(function(error) {

      status = {};

      reset();

      setup();

    });

  }

  function reset() {

      document.body.className = status.authenticated ? 'signed-in' : '';

      $('#player').empty();

      $('#installation, #team').css('display', 'none');

      location.hash = '';

  }

});

function temperature() {

  $(function () {
    $(document).ready(function() {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });

        var chart;

        $('#container').highcharts({
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function() {

                      var series = this.series[0];

                      socket.on('temperature', function(message) {
                        console.log(message);
                        var x = (new Date()).getTime(), // current time
                            y = parseFloat(message.value);
                        series.addPoint([x, y], true, true);

                      });
                    }
                }
            },
            title: {
                text: 'Temperature'
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: 'Value'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                        Highcharts.numberFormat(this.y, 2);
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Temperature data',
                data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;

                    for (i = -2; i <= 0; i++) {
                        data.push({
                            x: time + i * 1000,
                            y: 0
                        });
                    }
                    return data;
                })()
            }]
        });
    });

  });

}