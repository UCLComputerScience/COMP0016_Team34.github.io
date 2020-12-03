const language = require('@google-cloud/language');
const fs = require('fs');
const google = require("./google.js")


// Based on code from https://googleapis.dev/nodejs/language/latest/index.html
// Google
// accessed 30/11/20

exports.get_keywords_google = async function get_keywords_google(text,correct) {
  // Imports the Google Cloud client library
  // Instantiates a client
  const client = new language.LanguageServiceClient();

  // The text to analyze
  const document = {
    content: text,
    type: 'PLAIN_TEXT',
  };

  // Detects the sentiment of the text
  const [result] = await client.analyzeEntities({document: document});
  words = []
  for( entity of result.entities){
    words.push(entity.name)
  }
  if(JSON.stringify(words) == JSON.stringify(correct)){
    fs.appendFile("Result_Google.txt",JSON.stringify(words)+"\n",x=>{})
}

}


exports.test_google = function test_google(xs,corrects){
  for(x of xs){
      google.get_keywords_google(x,corrects[xs.indexOf(x)])
  }

}


//get_keywords_google("My mother fell down the stairs",['mother','stairs'])