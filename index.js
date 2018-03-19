var express = require('express');
var bodyParser = require('body-parser');
var app = express();
app.use(bodyParser.urlencoded({ extended: true }));
var PORT = process.env.PORT || 3000; 
// app.use(express.bodyParser());
var PythonShell = require('python-shell');
// pyshell.send('frank');

// var PORT = process.env.PORT;

  app.use(bodyParser.json());
 // var pyshell;
var name,result;
//   app.post('/name',function(req,res){
// console.log(req.body.name);
// name = req.body.name;
// var pyshell = new PythonShell('./name.py');

// pyshell.send(name);
// pyshell.on('message', function (message) {
//   // received a message sent from the Python script (a simple "print" statement)
//   console.log(message);
// result = message;
// });
// pyshell.end(function (err,code,signal) {
//   if (err) throw err;
//   console.log('The exit code was: ' + code);
//   console.log('The exit signal was: ' + signal);
//   console.log('finished');

// });

// });
app.get('/names/:name',function(req,res){
// res.send(req.params.name);
name = req.params.name;
var pyshell = new PythonShell('./name.py');

pyshell.send(name);
pyshell.on('message', function (message) {
  // received a message sent from the Python script (a simple "print" statement)
  console.log(message);
result = message;
console.log(name + " is a " + result + " name");
// res.send(name + " is a " + result + " name");
res.setHeader('Content-Type', 'application/json');

res.write(JSON.stringify({gender: result}));
});

pyshell.end(function (err,code,signal) {
  // if (err) throw err;
  if(err) console.log(err);
  console.log('The exit code was: ' + code);
  console.log('The exit signal was: ' + signal);
  console.log('finished');

});
});

app.get('/name',function(req,res){
res.send('thank you for your response');
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

app.listen(PORT);
console.log("started on : " + 3000);