const fs = require('fs');
const ms = require('./ms.js');


// Based on  code from https://docs.microsoft.com/en-us/azure/cognitive-services/text-analytics/quickstarts/text-analytics-sdk?pivots=programming-language-javascript&tabs=version-3#key-phrase-extraction 
// Microsoft
// accessed 30/11/20
const { TextAnalyticsClient, AzureKeyCredential } = require("@azure/ai-text-analytics");

const key = 'key';
const endpoint = 'https://nlp-test-ms.cognitiveservices.azure.com/';

const client = new TextAnalyticsClient(endpoint,  new AzureKeyCredential(key));

exports.get_keywords_ms =  async function get_keywords_ms(text,correct){

    const keyPhrasesInput = [text];
    const keyPhraseResult = await client.extractKeyPhrases(keyPhrasesInput);
    
    keyPhraseResult.forEach(result => {
        if(JSON.stringify(result.keyPhrases) == JSON.stringify(correct)){
            fs.appendFile("Result_MS.txt",JSON.stringify(result.keyPhrases)+"\n",x=>{})
        }
    });
}


exports.test_ms = function test_ms(xs,corrects){
    for(x of xs){
        ms.get_keywords_ms(x,corrects[xs.indexOf(x)])
    }
}

// var  xs = ["I have a headache and a cat","This is the moon","My mother fell down the stairs"]
// var corrects = [["headache", "cat"],["moon"],["mother","stairs"]]

// fs.truncate('Result_MS.txt', 0, function(){})
// test_ms(xs,corrects)
