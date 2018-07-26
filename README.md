## Train Marshalling Problem

This project is about resolving a train marshalling yard model. Initially, we have 6 train lines that contains several train cars going to differenet destinatins.
The goal is to sort and organize these cars for a certain destination using a small locomotive with the least movements been made. The following constraints are applied:
• Each line has a certain capacity (initially 10)
• Maximum 3 train cars at the time can be moved by the locomotive.


## What is used?

Java factory design pattern, Comparator, priority queue and hashmap.

## How to use?

Just clone the project and you can modify the inputs in Main.java.
You can add a line in the following format:

lines.put(0, L.makeLine("DGCDG",10));

The "DGCDG" is the line order and it means that 6 train cars with destination labels D, G, C, D and G are put in the line. "10" represents this line's capacity.

And you can set the destination in the following format (in this example the destination is C):

Marshaller m = new Marshaller(lines,'C');


Copyright Amir Sadra Khorramizadeh