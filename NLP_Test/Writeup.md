**<u>NLP Keyword Extraction</u>**

3 NLP API's were used to extract keywords from a set of tests. They were chosen as they are from large companies and has easy node.js support for their APIs. JavaScript in Node was used to write the tests (see below).

| API       | Correct Matches (out of 25) | Price                                                        |      |
| --------- | --------------------------- | ------------------------------------------------------------ | ---- |
| Google    | 16                          | 0-5,000 unit'* free.    5,000 - 1,000,000 $1/1000 units      |      |
| IBM       | 15                          | \$0.003/NLU** 1-250,000 items.  $0.001 NLU *next* 250,001 to 5,000,000 items |      |
| Microsoft | 15                          | 10,000/ month for free. Speech not included or £1.118 per 1,000 transactions*** (text) and £4.100 per 1,000 transactions (Speech) |      |

*"Documents that have more than 1,000 Unicode characters (including whitespace characters and any markup characters such as HTML or XML tags) are considered as multiple units, one unit per 1,000 characters." - https://cloud.google.com/natural-language/pricing

** \# of NLU Items = # of Data Units * # of Enrichment Features. 1 Data Unit = 1 group of 10,000 characters or less - https://www.ibm.com/cloud/watson-natural-language-understanding/pricing

*** For text requests, a transaction is an API call with query length up to 500 characters. - https://azure.microsoft.com/en-gb/pricing/details/cognitive-services/language-understanding-intelligent-services/









