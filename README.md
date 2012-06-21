js-scala-parser-combinator
==========================

# JavaScript embedded in Scala with paser combinators #

### Setup

1. Setup [js-scala](https://github.com/js-scala/js-scala). Use `sbt` `publish-local` to make js-scala available locally

2. Copy the file `local.properties` from virtualization-lms-core to this root project directory. See sample local.properties.sample

3. Run `sbt`. `run` and the sample code will create a js file called generated.js.

4. You can execute it with [rhino](http://www.mozilla.org/rhino/) doing the command `rhino generated.js`

### Further work

* Research producing more elaborate js code
* Find out how to do recursion
* Start parser combinator work

### Change log

#### 0.1

Proof of concept to make sure the js-scala can be called from another project