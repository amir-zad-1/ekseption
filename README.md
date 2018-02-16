# ekseption
ekseption is a java tool that finds Anti-patterns (not-well-handled) exceptions in a codebase.


(i) error handler (e.g., catch block) is empty;

(ii) error handler over-catches exceptions and aborts; and

(iii) error handler contains phrases like "TODO” and “FIXME”


Then it generates a HTML report.