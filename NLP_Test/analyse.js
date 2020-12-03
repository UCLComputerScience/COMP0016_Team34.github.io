const fs = require('fs');

function analyse(filename){
    fs.readFile(filename,(err, data) => {
        if (err) throw err;
        lines = data.toString().split("\n").length-1
        console.log(filename, lines)
    });
}

analyse("Result_IBM.txt")
analyse("Result_MS.txt")
analyse("Result_Google.txt")
