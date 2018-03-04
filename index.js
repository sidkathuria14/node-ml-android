var express = require('express');
var bodyparser = require('body-parser');
var app = express();
// app.use(express.bodyParser());
var PythonShell = require('python-shell');
var pyshell = new PythonShell('./name.py');
pyshell.send('frank');
pyshell.on('message', function (message) {
    // received a message sent from the Python script (a simple "print" statement)
    console.log(message);
  });
// var PORT = process.env.PORT;
pyshell.end(function (err,code,signal) {
    if (err) throw err;
    console.log('The exit code was: ' + code);
    console.log('The exit signal was: ' + signal);
    console.log('finished');
    console.log('finished');
  });
app.get('/',function(req,res){
//     const { spawn } = require('child_process');
//     const pyProg = spawn('python',['./name.py']);

//     pyProg.stdout.on('data', function(data) {

//         console.log(data.toString());
//         res.write(data);
//         res.end('end');
// });
res.sendFile(__dirname + '/index.html');

    // console.log(req.body.name);

});

app.listen(3000);
console.log("started on : " + 3000);