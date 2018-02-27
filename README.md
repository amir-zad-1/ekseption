# ekseption
Java tool that finds Anti-patterns (not-well-handled) exceptions in a codebase.


(i) error handler (e.g., catch block) is empty;

(ii) error handler over-catches exceptions and aborts; and

(iii) error handler contains phrases like "TODO” and “FIXME”


Then it generates a HTML report in the ekseption/output/.
The screen-shot below is the output file run against [ElasticSearch](https://github.com/elastic/elasticsearch) Repo 


Team Members
1) 40039878 Amir Mohsen Hossein Zadeh
2) 40059790 Kartika Patil
3) 40035954 Jesus Onofre Diaz
4) 27670036 Aida Rangy
5) 40018926 Parisa Nikzad

```sh
# This is SOEN691 Assignment project. 
```

![Report file](https://www.dropbox.com/s/cz23bujv3lh9jnu/Screenshot%20from%202018-02-25%2018-18-31.png?raw=1)