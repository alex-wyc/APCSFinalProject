APCSFinalProject
================

APCS Final Project - Athenian <sub><sup>Rabbit</sub></sup> <sub><sup><sub><sup>Browser</sub></sup></sub></sup>

File hierarchy:
---------------
* A class that connects to specific server
* A class the translates pieces of html code into gnome terminal formatting


Folder Descriptions:
--------------------
* <code>src</code>: the folder that holds the source code for the project, run either by <code>./recompile</code> or <code>javac Driver.java</code> followed by <code>java Driver</code>
* <code>testCases</code>: the folder that holds websites that actually work, slightly edited --> first line points to the actual url of the website

Random Joke that is SOMEWHAT Appropriate
=========================================	

Top 10 things likely to be overheard from a Klingon Programmer

* Specifications are for the weak and timid!
* You question the worthiness of my code? I should kill you where you stand!
* Indentation? I will show you how to indent when I indent your skull!
* What is this talk of release? Klingons do not <em>release software</em>. Our software <em>ESCAPES</em>, leaving a bloody trail of designers and quality assurance people in its wake.
* Klingon function calls do not have parameters--they have arguments--and they ALWAYS WIN THEM.
* Debugging? Klingons do not debug. Our software does not coddle the weak.
* A True Klingon Warrior does not comment in his code!
* Klingon software does not have BUGS. It has FEATURES, and those features are too sophisticated for a Romulan pig like you to understand.
* You cannot truly appreciate Dilbert unless you've read it in the original Klingon.
* Our users will know fear and cower before our software! Ship it! <b>Ship it and let them flee like the dogs they are!</b>

ChangeLog
=========

2014-12-22: Yicheng - Basic formatting class added, can read color, bold, italic, underline and strikethrough

2014-12-26: Yicheng - URL-to-source code added; returns entire source, body and/or head from specified url

2014-12-27: Yicheng - Tried to implement regex to search for head, title, paragraph, body and other stuff, WIP

2015-01-01: Yicheng - Finally fixed regex... adding support for subtitles within the body and better paragraph support, still have to take care of tables and lists

2015-01-06: Dalton - Nothing but typo correcting

2015-01-06: Yicheng - List-handling class half working

2015-01-07: Dalton - <del>Last time</del> <ins>One of many times</ins> I try to fix Yicheng's spelling; made some tables stuff

2015-01-12: Yicheng - Finally fixed infinite while loop/recursion problem in list formatter; something is still broken nonetheless.

2015-01-14: Yicheng - List formatter completely working, can handle nested lists

2015-01-14: Yicheng - Paragraph formatter done!!!

2015-01-14: Yicheng - Last problem (nested html...) half-working. Discovered <a href="http://stackoverflow.com/questions/1732348/regex-match-open-tags-except-xhtml-self-contained-tags/1732454#1732454">this...</a>

2015-01-20: Yicheng/Dalton - Table class finally almost working... Finally, it prints stuff, just have to fix the indentation.

TO-DO LIST
===========
- [x] A list-handling class (Yicheng)
- [ ] A table-handling class
- [x] A paragraph formatter (Yicheng)
- [ ] A hyperlink-handling class
