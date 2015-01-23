APCSFinalProject
================

![Athenian Rabbit Browser Logo][Athenian Rabbit Browser]

[Athenian Rabbit Browser]: https://raw.githubusercontent.com/alex-wyc/APCSFinalProject/master/AthenianRabbitBrowser.gif "Logo"

APCS Final Project - Athenian <sub><sup>Rabbit</sub></sup> <sub><sup><sub><sup>Browser</sub></sup></sub></sup>

Project Description:
--------------------

In this project, we attempt to write a html parser with the aid of regular expressions in java.

I understand that it is impossible to write a full html parser with just regular expressions, as demonstrated by 
<a href="http://stackoverflow.com/questions/1732348/regex-match-open-tags-except-xhtml-self-contained-tags/1732454#1732454">this post on stackoverflow</a>

However, the project can open a lot of simple (well-written) websites effectively. It has a working list formatter which can handel sublists,
a working table formatter and paragraph formatter. It also converts html symbols into ascii/unicode symbols with the aid of
<a href="http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringEscapeUtils.html">this apache patch</a>.

Websites that it can open correctly and effectively include:
* http://nethackwiki.com/wiki/Main_Page and literally everything on the nethackwiki
* The non-js loading part of http://www.sciencemag.org/
* Facebook doesn't "load" per-se, but it does not crash either.
* Google loads and tells you to install google chrome

File hierarchy:
---------------
* A class that connects to specific server
* A class the translates pieces of html code into gnome terminal formatting


Folder Descriptions:
--------------------
* <code>src</code>: the folder that holds the source code for the project, run either by <code>./recompile</code> or <code>javac Driver.java</code> followed by <code>java Driver</code>

Work Log
--------

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

2015-01-20: Yicheng - FINALLY fixed the nested html problem, so very happy, the browser can finally open <a href="http://nethackwiki.com/wiki/Scroll">this</a> correctly. Also some basic boardering stuff added. Also, basic hyperlink stuff added

2015-01-22: Yicheng/Dalton - Finishing touches, try-catch with system default browser option added.

TO-DO LIST
----------
- [x] A list-handling class
- [x] A table-handling class
- [x] A paragraph formatter
- [x] A whole load of HTML codes using (<code>org.apache.commons.lang3.StringEscapeUtils</code>)
- [x] A hyperlink-handling class (kinda)

Sources Cited
--------------
Apache HTML code translator: http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringEscapeUtils.html

And as always, a good joke:
---------------------------

Top 10 things likely to be overheard from a Klingon Programmer

* Specifications are for the weak and timid!
* You question the worthiness of my code? <em>I should kill you where you stand!</em>
* Indentation? <em>I will show you how to indent when I indent your skull!</em>
* What is this talk of release? Klingons do not release software. Our software ESCAPES, leaving a bloody trail of designers and quality assurance people in its wake.
* Klingon function calls do not have parameters. They have arguments--and they ALWAYS WIN THEM.
* Debugging? Klingons do not debug. Our software does not coddle the weak.
* A True Klingon Warrior does not comment in his code!
* Klingon software does not have BUGS. It has FEATURES, and those features are too sophisticated for a Romulan pig like you to understand.
* You cannot truly appreciate Dilbert unless you've read it in the original Klingon.
* Our users will know fear and cower before our software! Ship it! <b>Ship it and let them flee like the dogs they are!</b>

