# Dat Logic

An experiment in utilzing datascript and it's datalog query engine
for logic programming. The goal here is to determine the usefulness
of logic programming in everyday application development.

In his great and well-known talk ["Simple Made Easy"](https://github.com/matthiasn/talk-transcripts/blob/master/Hickey_Rich/SimpleMadeEasy.md), Rich Hickey almost casually condemned conditional statements
as unnecessarily complex. He went no further in explaining this sentiment about one of the few things
most software-engineers take as a given. In his words:

    > Conditionals are complex in interesting ways, and rules can be simpler.

It took some thinking on my end, before this began to make sense. Replacing conditionals
with a logic engine and composable rule sets would come with certain implications. With
this project, I hope to explore some of them:

**Explicitly writing logic statements** should help with thinking clearly about the problems at hand.

**Decoupling logic from program flow.**
Program or business logic should be defined only
once and all in one place. This puts specification and program a lot closer together.
But conditional statements tend to be distributed all over our program.

**Make logic an explicit thing**, that can be isolated from the rest of the source.
This would allow users and customers of our software, to modify and adapt the programs logic
to their specific needs.

**Correctly deal with lots of interdependent constraints.**
Doing this by hand can be very cumbersome and error-prone, because we have to keep
all rules in our head while writing a system that implicitely adheres to them.
Specifying rules directly takes out the extra step. Technologies like *AutoLayout* demonstrate
the power of automatic constraint solving in complex software.

This could also improve how edge cases get handled in our software, especially with
complex UIs. 

Logic statements could of course be used to describe any other constraints we'd like to
enforce on our system. A set of rules could be ran over the application state after every
transaction, allowing for powerful consistency and correctness checks across the system
that could even beat the expresiveness of a static type system.


On the other hand, a logic engine will currently perform a lot worse performance-wise and might even
get caught up in an infinite search. It also might add a layer of "magic" to debugging our program.