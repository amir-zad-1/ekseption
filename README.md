# ekseption
ekseption is a java tool that finds Anti-patterns (not-well-handled) exceptions in a codebase.


(i) error handler (e.g., catch block) is empty;

(ii) error handler over-catches exceptions and aborts; and

(iii) error handler contains phrases like "TODO” and “FIXME”


Then it generates a HTML report.

![Report file](https://www.dropbox.com/s/03kky10hw0kbiq1/Screenshot%20from%202018-02-19%2019-02-57.png?raw=1)