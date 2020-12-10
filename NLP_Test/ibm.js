const NaturalLanguageUnderstandingV1 = require('ibm-watson/natural-language-understanding/v1');
const { IamAuthenticator } = require('ibm-watson/auth');
const fs = require('fs');
const ibm = require('./ibm.js');



const naturalLanguageUnderstanding = new NaturalLanguageUnderstandingV1({
  version: '2020-08-01',
  authenticator: new IamAuthenticator({
    apikey: 'key',
  }),
  serviceUrl: 'url',
});

// Based on code from https://cloud.ibm.com/apidocs/natural-language-understanding?code=node#keywords
// IBM
// accessed 30/11/20


exports.get_keywords_ibm = function get_keywords_ibm(text,correct){
    
    analyzeParams = {
        'text':text,
        'language':'en',
        'features': {
        'keywords': {
            'sentiment': false,
            'emotion': false,
        }
        }
    };

    naturalLanguageUnderstanding.analyze(analyzeParams).then(AnalysisResult => { 
        json = JSON.parse(JSON.stringify(AnalysisResult, null, 2)).result.keywords
        var result = []
        for(keyword of json){
            result.push(keyword.text)
        }
        console.log(result)
        return result
    }).then(result => {
        if(JSON.stringify(result) == JSON.stringify(correct)){
            fs.appendFile("Result_IBM.txt",JSON.stringify(result)+"\n",x=>{})
        }
    })
}   

exports.test_ibm = function test_ibm(xs,corrects){
    for(x of xs){
        ibm.get_keywords_ibm(x,corrects[xs.indexOf(x)])
    }

}


// xs = ["I have a headache and a cat","This is the moon","My mother fell down the stairs"]
// correct = [["headache", "cat"],["moon"],["mother","stairs"]]

// fs.truncate('Result_IBM.txt', 0, function(){})
// test_ibm(xs,correct)
