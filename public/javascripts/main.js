var svp;

var apiUrl = "http://localhost:9000";
var game = {};
var gameLoop = setInterval(gameTick, 1000);
game.userId = "";

function gameTick() {
  if($ === undefined) return;
  console.log("Tick");
  if(game.timeLeft > 0) {
    game.timeLeft--;
  }

  if(game.timeLeft == 0 && !game.modalIsOpen) {
      document.getElementById('OpenTheModal').click();
  }

  if(game.timeLeft < 0) {
    $("#timeleft").html("");
  } else {
    $("#timeleft").html("Time left: " + game.timeLeft + "s");
  }
}

function submitGuess() {
  var postData = {userId: game.userId, name: game.userName, guess: game.guess};
  $.post(apiUrl + "/location", postData, function(resp) {
      $("#timeleft").html("Waiting for the round to end...");
  });
}

function getCurrentLocation($) {
  document.getElementById('OpenTheModal').click();
  var url = apiUrl + "/location";
  $.ajax(url, {
    success: function(data) {
      console.log("Location");
      console.log(data);
      game.timeLeft = data.timeleft;

      svp = new google.maps.StreetViewPanorama(document.getElementById("streetview"), {
        addressControl: false,
        linksControl: true,
        position: new google.maps.LatLng(data.location.lat, data.location.lng),
        visible: false,
        pov: {
        heading: Math.floor(Math.random()*360),
        pitch: +5
        }
      });
      svp.controls[google.maps.ControlPosition.RIGHT_TOP].push(document.getElementById("mycontrol"));

      svp.setVisible(true);
    }
  });
}

$(document).ready(function() {
  //getCurrentLocation($);
});

var initialize = function() {

  var cookies = {
    set: function(name,value,days) {
      if (days) {
        var date = new Date();
        date.setTime(date.getTime()+(days*24*60*60*1000));
        var expires = "; expires="+date.toGMTString();
      } else {
        var expires = "";
      }
      document.cookie = name+"="+value+expires+"; path=/";
    },

    read: function(name) {
      var nameEQ = name + "=";
      var ca = document.cookie.split(';');
      for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
      }
      return null;
    },

    erase: function(name) {
      createCookie(name,"",-1);
    }
  }

  var pinColor = "00FF00";
  var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
      new google.maps.Size(21, 34),
      new google.maps.Point(0,0),
      new google.maps.Point(10, 34));
  var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
      new google.maps.Size(40, 37),
      new google.maps.Point(0, 0),
      new google.maps.Point(12, 35));
  pinColor = "0000FF";
  var pinLocationImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
    new google.maps.Size(21, 34),
    new google.maps.Point(0,0),
    new google.maps.Point(10, 34));
  var pinLocationShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
    new google.maps.Size(40, 37),
    new google.maps.Point(0, 0),
    new google.maps.Point(12, 35));

  var user_info = null;
  var end_time = null;
  var next_round = null;
  var guesses = {};
  var timeLeftDiv = document.getElementById("timeleft");

  /**svp = new google.maps.StreetViewPanorama(document.getElementById("streetview"), {
    addressControl: false,
    linksControl: true,
    position: new google.maps.LatLng(53.474, -2.248),
    visible: false,
    pov: {
      heading: Math.floor(Math.random()*360),
      pitch: +5
    }
    svp.controls[google.maps.ControlPosition.RIGHT_TOP].push(document.getElementById("mycontrol"));

  svp.setVisible(true);
  });*/
  getCurrentLocation($);
  

  var map = new google.maps.Map(document.getElementById("smallmap"), {
    zoom: 0,
    center: new google.maps.LatLng(53.474, -2.248),
    keyboardShortcuts: false,
    streetViewControl: false,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  });

  var guessMarker = null;

  if(guessMarker !== null) {
    guessMarker.setVisible = false;
    guessMarker.setMap(null);
  }

  google.maps.event.addListener(map, 'click', function(event) {
    makeGuess(event.latLng);
  });

  function makeGuess(place) {
    game.guess = place;
    if(guessMarker !== null) {
      guessMarker.setVisible = false;
      guessMarker.setMap(null);
    }
    guessMarker = new google.maps.Marker({
      position: place,
      map: map,
//      title: user_info.name,
      clickable: false,
      icon: pinImage,
      shadow: pinShadow
    });
  }

//  function updateTime() {
//    if(end_time !== null) {
//      if(moment().isBefore(end_time)) {
//        timeLeftDiv.innerHTML = "Time left: " + end_time.diff(moment(), "seconds") + " seconds";
//      } else {
//        timeLeftDiv.innerHTML = "Finished.";
//        if(next_round !== null) {
//          timeLeftDiv.innerHTML = timeLeftDiv.innerHTML + " Next rount starts in " + next_round.diff(moment(), "seconds") + " seconds";
//        }
//      }
//    } else {
//      timeLeftDiv.innerHTML = "Time left: unknown"
//    }
//  }
//
//  function min(x, y) {
//    if(x<y)
//      return x;
//    else
//      return y;
//  }

   document.getElementById('OpenTheModal').click();
}

window.onload = function() {
  var script = document.createElement("script");
  script.type = "text/javascript";
  script.src = 'http://maps.googleapis.com/maps/api/js?key=AIzaSyB-9UdzjbK_V2-2W5HaF-j26eph2woqkCM&sensor=false&callback=initialize';
  document.body.appendChild(script);
  script = document.createElement("script");
  script.type = "text/javascript";
  script.src = "assets/javascripts/modal.js";
  document.body.appendChild(script);
}
