const ms = require('./ms.js');
const ibm = require('./ibm.js');
const google = require("./google.js")
const tests = require('./json/test_words_4.json')

const fs = require('fs');


// fs.truncate('Result_MS.txt', 0, function(){})
// fs.truncate('Result_IBM.txt', 0, function(){})
// fs.truncate('Result_Google.txt', 0, function(){})



for(test of tests.words){
    ms.get_keywords_ms(test.text,test.keywords)
    ibm.get_keywords_ibm(test.text,test.keywords)
    google.get_keywords_google(test.text,test.keywords)
    setTimeout(x=>{},1000)
}
