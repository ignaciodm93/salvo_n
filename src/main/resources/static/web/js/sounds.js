



function playShot(){
var aud = new Audio();
aud.src = './sounds/shot.wav';

//You could also do
//var aud = new Audio('sound.ogg');

//Now lets play the music
aud.play();
}


function playShoot(){
var aud = new Audio();
aud.src = './sounds/explo.ogg';
aud.play();
}



//Para Games, no funciona.
function backgroundMusicGames(){
var aud = new Audio();
aud.src = './sounds/pir.mp3';
aud.volume =  0.2;
aud.play();
}


//Para game
function backgroundMusicGame(){


myAudio = new Audio();
myAudio.src = './sounds/as.mp3';
myAudio.volume =  0.2;
if (typeof myAudio.loop == 'boolean')
{
    myAudio.loop = true;
}
else
{
    myAudio.addEventListener('ended', function() {
        this.currentTime = 0;
        this.play();
    }, false);
}
myAudio.play();
}




function playLogin(){
var aud = new Audio();
aud.src = './sounds/log.wav';
aud.volume =  0.2;
aud.play();
}

function playFail(){
var aud = new Audio();
aud.src = './sounds/fail.wav';
aud.play();
}

function playWin(){
var aud = new Audio();
aud.src = './sounds/indi.mp3';
aud.play();
myAudio.volume =  0.6;
}


